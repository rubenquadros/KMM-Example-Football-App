package com.ruben.footiescore.android.ui.favteam

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BallLoader
import com.ruben.footiescore.android.ui.common.TopSearchBar
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 26/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.SelectTeamScreen(
    selectFavTeamViewModel: SelectFavTeamViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    var searchState by remember { mutableStateOf(TextFieldValue())}
    val density = LocalDensity.current

    fun onValueChanged(textFieldValue: TextFieldValue) {
        searchState = textFieldValue
    }

    fun onSearchCleared() {
        searchState = TextFieldValue()
    }

    fun onSearch(searchQuery: String) {
        selectFavTeamViewModel.searchTeam(searchQuery)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = selectFavTeamViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = selectFavTeamViewModel.createInitialState())

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopSearchBar(
                    searchState = searchState,
                    onValueChanged = { textFieldValue -> onValueChanged(textFieldValue) },
                    onClear = { onSearchCleared() },
                    onSearch = { searchQuery -> onSearch(searchQuery) }
                )

                if (state is SelectFavTeamState.SearchResultState) {
                    SearchResults()
                }

            }

            AnimatedVisibility(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .align(Alignment.TopCenter)
                    .animateEnterExit(
                        enter = slideInVerticallyAnim(
                            offset = with(density) { -100.dp.roundToPx() },
                            duration = 600
                        ) + fadeIn(),
                        exit = slideOutVerticallyAnim(offset = with(density) { 100.dp.roundToPx() }) + fadeOut()
                    ),
                visible = state is SelectFavTeamState.InitialState
            ) {
                InitialStateContent()
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = state is SelectFavTeamState.LoadingState
            ) {
                BallLoader(
                    modifier = Modifier.size(40.dp),
                    ballColor = FootieScoreTheme.colors.primary
                )
            }
        }
    }

    BackHandler {
        //cannot go back from here
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InitialStateContent() {
    Column {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.select_team_title),
            style = FootieScoreTheme.typography.button,
            color = FootieScoreTheme.colors.surface,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {

                },
            text = stringResource(id = R.string.select_team_skip),
            style = FootieScoreTheme.typography.button,
            color = FootieScoreTheme.colors.disabled,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.SearchResults() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Initial State")
@Composable
fun PreviewInitialStateContent() {
    InitialStateContent()
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Search content")
@Composable
fun PreviewSelectTeamScreen() {
    AnimatedVisibility(visible = true) {
        SearchResults()
    }
}