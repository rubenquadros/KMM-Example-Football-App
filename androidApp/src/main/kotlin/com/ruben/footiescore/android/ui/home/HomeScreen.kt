package com.ruben.footiescore.android.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.entity.AllCompetitionEntity
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

/**
 * Created by Ruben Quadros on 17/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = getViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    var isVisible by remember {
        mutableStateOf(false)
    }

    when (state) {
        is HomeState.InitialState -> {
            isVisible = true
        }
        is HomeState.LoadingState -> {
            isVisible = true
        }
        is HomeState.AllCompetitionsState -> {
            isVisible = false
            CompetitionsView(
                modifier = Modifier.fillMaxSize(),
                competitions = (state as HomeState.AllCompetitionsState).competitions
            )
        }
        else -> {
            isVisible = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PitchLoader(
            modifier = Modifier.fillMaxSize(),
            isVisible = isVisible
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompetitionsView(modifier: Modifier = Modifier, competitions: List<AllCompetitionEntity>) {
    val topContentHeight = 200.dp
    val topContentHeightPx = with(LocalDensity.current) { topContentHeight.roundToPx().toFloat() }
    var topContentOffsetHeightPx by remember {
        mutableStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = topContentOffsetHeightPx + delta
                topContentOffsetHeightPx = newOffset.coerceIn(-topContentHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(topContentHeight)
            .offset {
                IntOffset(x = 0, y = topContentOffsetHeightPx.roundToInt())
            }) {
            Column(modifier = Modifier.fillMaxWidth().background(FootieScoreTheme.colors.primary)) {
                Text(
                    text = "FootieScrore",
                    color = FootieScoreTheme.colors.onPrimary
                )

                Text(
                    text = "nice sentence",
                    color = FootieScoreTheme.colors.onPrimary
                )
            }

        }

        LazyVerticalGrid(
            modifier = modifier,
            cells = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(top = topContentHeight)
        ) {
            items(competitions) { competition ->
                CompetitionRow(
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth(), competition = competition
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CompetitionRow(modifier: Modifier = Modifier, competition: AllCompetitionEntity) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = FootieScoreTheme.shapes.largeRoundCornerShape,
        elevation = 10.dp
    ) {
        Column {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight,
                painter = rememberImagePainter(
                    data = competition.image,
                    builder = {
                        placeholder(R.drawable.ic_football_black)
                        crossfade(true)
                    }
                ),
                contentDescription = stringResource(id = R.string.content_description_competition_image)
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = competition.name,
                    color = FootieScoreTheme.colors.surface,
                    style = FootieScoreTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(name = "Competition")
@Composable
fun PreviewCompetitionRow() {
    CompetitionRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        competition = AllCompetitionEntity(1, "Campeonato Brasileiro Série A", "")
    )
}

@Preview(name = "All Competitions")
@Composable
fun PreviewCompetitionsView() {
    CompetitionsView(
        modifier = Modifier.fillMaxSize(),
        competitions = listOf(
            AllCompetitionEntity(1, "Campeonato Brasileiro Série A", ""),
            AllCompetitionEntity(2, "Championship", ""),
            AllCompetitionEntity(3, "UEFA Champions League", ""),
            AllCompetitionEntity(4, "European Championship", ""),
            AllCompetitionEntity(5, "Premier League", ""),
            AllCompetitionEntity(6, "FIFA World Cup", "")
        )
    )
}