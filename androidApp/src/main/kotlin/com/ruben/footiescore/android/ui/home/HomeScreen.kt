package com.ruben.footiescore.android.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.common.EmptyBackHandler
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.home.component.BrowseContent
import com.ruben.footiescore.android.ui.home.component.RecentMatchesContent
import com.ruben.footiescore.android.ui.home.component.UserDetailsContent
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.RecentMatchesEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.HomeScreen(
    homeViewModel: HomeViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is HomeState.DashBoardState -> {
                    val dashBoardState = state as? HomeState.DashBoardState
                    dashBoardState?.let {
                        DashboardContent(
                            scrollState = scrollState,
                            isUserLoggedIn = it.isUserLoggedIn,
                            userDetails = it.userDetails,
                            teamMatches = it.teamMatchesDetails
                        )
                    }
                }

                is HomeState.ErrorState -> {
                    ErrorView(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.TopCenter),
                        errorMessage = stringResource(id = R.string.all_generic_error),
                        enterTransition = slideInVerticallyAnim(
                            offset = with(density) { -100.dp.roundToPx() },
                            duration = 600
                        ) + fadeIn(),
                        exitTransition = slideOutVerticallyAnim(
                            offset = with(density) { 100.dp.roundToPx() },
                            duration = 300
                        ) + fadeOut(),
                        onClick = { homeViewModel.initData() }
                    )
                }
                else -> { /*do nothing*/ }
            }

            PitchLoader(
                modifier = Modifier.fillMaxSize(),
                isVisible = state is HomeState.LoadingState
            )
        }
    }

    EmptyBackHandler()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.DashboardContent(
    scrollState: ScrollState,
    isUserLoggedIn: Boolean,
    userDetails: UserEntity?,
    teamMatches: List<RecentMatchesEntity>
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        UserDetailsContent(isUserLoggedIn, userDetails)

        RecentMatchesContent(recentMatches = teamMatches)

        BrowseContent(isTeamNotSelected = userDetails?.teamId == null)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewDashboardContent() {
    AnimatedVisibility(visible = true) {
        DashboardContent(
            scrollState = rememberScrollState(),
            isUserLoggedIn = true,
            userDetails = UserEntity("123", "Ruben Quadros", "", "", -1),
            teamMatches = listOf(
                RecentMatchesEntity(
                    id = 1,
                    competitionEntity = CompetitionEntity(
                        id = 2,
                        name = "UEFA Champions League",
                        area = AreaEntity(
                            code = "EUR   ",
                            name = "Europe",
                            areaUrl = "https://crests.football-data.org/EUR.svg"
                        )
                    ),
                    date = "08-12-2021",
                    homeTeam = TeamEntity(
                        id = 3,
                        name = "Manchester United FC",
                        shortName = "MUN",
                        crestUrl = ""
                    ),
                    awayTeam = TeamEntity(
                        id = 4,
                        name = "BSC Young Boys",
                        shortName = "YOB",
                        crestUrl = ""
                    ),
                    scoreEntity = ScoreEntity(
                        homeTeam = 1,
                        awayTeam = 1
                    )
                ),
                RecentMatchesEntity(
                    id = 5,
                    competitionEntity = CompetitionEntity(
                        id = 6,
                        name = "Premier League",
                        area = AreaEntity(
                            code = "ENG   ",
                            name = "England",
                            areaUrl = "https://crests.football-data.org/EUR.svg"
                        )
                    ),
                    date = "08-12-2021",
                    homeTeam = TeamEntity(
                        id = 7,
                        name = "Norwich City FC",
                        shortName = "NOR",
                        crestUrl = ""
                    ),
                    awayTeam = TeamEntity(
                        id = 3,
                        name = "Manchester United FC",
                        shortName = "MUN",
                        crestUrl = ""
                    ),
                    scoreEntity = ScoreEntity(
                        homeTeam = 0,
                        awayTeam = 1
                    )
                )
            )
        )
    }
}