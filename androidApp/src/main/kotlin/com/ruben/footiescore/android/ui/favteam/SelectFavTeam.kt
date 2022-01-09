package com.ruben.footiescore.android.ui.favteam

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BallLoader
import com.ruben.footiescore.android.ui.common.EmptyBackHandler
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.NoResultsView
import com.ruben.footiescore.android.ui.common.TopSearchBar
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.favteam.component.InitialStateContent
import com.ruben.footiescore.android.ui.favteam.component.SearchResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 26/11/21
 **/
@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AnimatedVisibilityScope.SelectTeamScreen(
    selectFavTeamViewModel: SelectFavTeamViewModel = getViewModel(),
    navigateToHome: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchState by remember { mutableStateOf(TextFieldValue())}
    val density = LocalDensity.current

    fun onValueChanged(textFieldValue: TextFieldValue) {
        searchState = textFieldValue
    }

    fun onSearchCleared() {
        selectFavTeamViewModel.onSearchCleared().also {
            searchState = TextFieldValue()
        }
    }

    fun onSearch(searchQuery: String) {
        selectFavTeamViewModel.searchTeam(searchQuery)
    }

    fun saveTeam(id: Int) {
        selectFavTeamViewModel.saveTeam(id)
    }

    HandleSideEffects(
        sideEffectFlow = selectFavTeamViewModel.uiSideEffect(),
        scaffoldState = scaffoldState,
        keyboardController = keyboardController,
        onSelectTeamSuccess = navigateToHome
    )

    DisposableEffect(Unit) {
        onDispose { keyboardController?.hide() }
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

                when (state) {
                    is SelectFavTeamState.InitialState -> {
                        InitialStateContent(
                            density = density,
                            onSkipClick = {
                                keyboardController?.hide()
                                navigateToHome.invoke()
                            }
                        )
                    }

                    is SelectFavTeamState.SearchResultState -> {
                        SearchResults(
                            modifier = Modifier.padding(top = 8.dp),
                            enterTransition = fadeIn(initialAlpha = 0.2f),
                            exitTransition = fadeOut(targetAlpha = 0.2f),
                            lazyListState = lazyListState,
                            searchResults = (state as? SelectFavTeamState.SearchResultState)?.searchResults.orEmpty(),
                            onClick = { id -> saveTeam(id) }
                        )
                    }

                    is SelectFavTeamState.NoResultsState -> {
                        NoResultsView(
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .align(Alignment.CenterHorizontally),
                            enterTransition = slideInVerticallyAnim(
                                offset = with(density) { -100.dp.roundToPx() },
                                duration = 600
                            ) + fadeInAnim(),
                            exitTransition = slideOutVerticallyAnim(
                                offset = with(density) { 100.dp.roundToPx() },
                                duration = 300
                            ) + fadeOutAnim(),
                            message = stringResource(id = R.string.all_no_results_found)
                        )
                    }

                    is SelectFavTeamState.ErrorState -> {
                        ErrorView(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.CenterHorizontally),
                            errorMessage = stringResource(id = R.string.all_generic_error),
                            enterTransition = slideInVerticallyAnim(
                                offset = with(density) { -100.dp.roundToPx() },
                                duration = 600
                            ) + fadeInAnim(),
                            exitTransition = slideOutVerticallyAnim(
                                offset = with(density) { 100.dp.roundToPx() },
                                duration = 300
                            ) + fadeOutAnim()
                        )
                    }
                }
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = state.shouldShowLoading
            ) {
                BallLoader(
                    modifier = Modifier.size(40.dp),
                    ballColor = FootieScoreTheme.colors.primary
                )
            }
        }
    }

    EmptyBackHandler()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HandleSideEffects(
    sideEffectFlow: Flow<SelectFavTeamSideEffect>,
    scaffoldState: ScaffoldState,
    keyboardController: SoftwareKeyboardController?,
    onSelectTeamSuccess: () -> Unit
) {
    val context = LocalContext.current
    val searchError = stringResource(id = R.string.all_generic_error)
    val sameTeamError = stringResource(id = R.string.select_team_already_selected_error)

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is SelectFavTeamSideEffect.ShowErrorMessage -> {
                    scaffoldState.snackbarHostState.showSnackbar(searchError)
                }

                is SelectFavTeamSideEffect.TeamAlreadySelected -> {
                    Toast.makeText(context, sameTeamError, Toast.LENGTH_SHORT).show()
                }

                is SelectFavTeamSideEffect.HideKeyboard -> {
                    keyboardController?.hide()
                }

                is SelectFavTeamSideEffect.SelectTeamSuccess -> {
                    keyboardController?.hide()
                    onSelectTeamSuccess.invoke()
                }
            }
        }
    }
}