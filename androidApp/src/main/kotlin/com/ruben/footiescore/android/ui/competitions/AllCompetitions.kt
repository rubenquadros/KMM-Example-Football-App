package com.ruben.footiescore.android.ui.competitions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.rememberImagePainter
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity

/**
 * Created by Ruben Quadros on 17/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.AllCompetitionsScreen(
    competitionsViewModel: CompetitionsViewModel
) {

    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = competitionsViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = competitionsViewModel.createInitialState())

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CompetitionsState.AllCompetitionsState -> {
                (state as? CompetitionsState.AllCompetitionsState)?.competitions?.let {
                    AllCompetitionsContent(
                        modifier = Modifier.fillMaxSize(),
                        competitions = it
                    )
                }
            }
            is CompetitionsState.ErrorState -> {
                ErrorView(
                    modifier = Modifier.align(Alignment.Center),
                    errorMessage = stringResource(id = R.string.all_generic_error),
                    enterTransition = slideInVerticallyAnim(
                        offset = with(density) { -100.dp.roundToPx() },
                        duration = 600
                    ) + fadeInAnim(),
                    exitTransition = slideOutVerticallyAnim(
                        offset = with(density) { 100.dp.roundToPx() },
                        duration = 300
                    ) + fadeOutAnim(),
                    onClick = { competitionsViewModel.initData() }
                )
            }
            else -> { /*do nothing*/
            }
        }

        PitchLoader(
            modifier = Modifier.fillMaxSize(),
            isVisible = state is CompetitionsState.LoadingState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedVisibilityScope.AllCompetitionsContent(
    modifier: Modifier = Modifier,
    competitions: List<AllCompetitionEntity>
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(top = 16.dp),
        cells = GridCells.Fixed(count = 2)
    ) {
        itemsIndexed(items = competitions) { index, competition ->
            CompetitionItem(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                index = index,
                competition = competition
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.CompetitionItem(modifier: Modifier = Modifier, index: Int, competition: AllCompetitionEntity) {
    val density = LocalDensity.current

    val (enterAnim, exitAnim) = if (index%2 == 0) {
        slideInHorizontallyAnim(
            offset = with(density) { -100.dp.roundToPx() },
            duration = 800
        ) + fadeInAnim()  to
        slideOutHorizontallyAnim(
            offset = with(density) { 100.dp.roundToPx() },
            duration = 400
        ) + fadeOutAnim()
    } else {
        slideInHorizontallyAnim(
            offset = with(density) { 100.dp.roundToPx() },
            duration = 800
        ) + fadeInAnim() to
        slideOutHorizontallyAnim(
            offset = with(density) { -100.dp.roundToPx() },
            duration = 400
        ) + fadeOutAnim()
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .animateEnterExit(
                enter = enterAnim,
                exit = exitAnim
            ),
        shape = FootieScoreTheme.shapes.largeRoundCornerShape,
        elevation = 10.dp
    ) {
        Column {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight,
                painter = if (competition.image.isEmpty()) {
                    painterResource(id = R.drawable.ic_football_black)
                } else {
                    rememberImagePainter(
                        data = competition.image,
                        builder = {
                            placeholder(R.drawable.ic_football_black)
                            crossfade(true)
                        }
                    )
                },
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
    AnimatedVisibility(visible = true) {
        CompetitionItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            competition = AllCompetitionEntity(1, "Campeonato Brasileiro Série A", ""),
            index = 0
        )
    }
}

@Preview(name = "All Competitions")
@Composable
fun PreviewCompetitionsView() {
    AnimatedVisibility(visible = true) {
        AllCompetitionsContent(
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
}