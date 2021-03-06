package com.ruben.footiescore.android.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.home.component.FeedContent
import com.ruben.footiescore.android.ui.home.component.RecentMatchesContent
import com.ruben.footiescore.android.ui.home.component.UserDetailsContent
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.MatchEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.HomeScreen(
    homeViewModel: HomeViewModel,
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is HomeState.DashBoardState -> {
                val dashBoardState = state as? HomeState.DashBoardState
                dashBoardState?.let {
                    DashboardContent(
                        scrollState = scrollState,
                        isUserLoggedIn = it.isUserLoggedIn,
                        userDetails = it.userDetails,
                        teamMatches = it.teamMatchesDetails,

                    )
                }
            }

            is HomeState.ErrorState -> {
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.DashboardContent(
    scrollState: ScrollState,
    isUserLoggedIn: Boolean,
    userDetails: UserEntity?,
    teamMatches: List<MatchEntity>
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        UserDetailsContent(isUserLoggedIn, userDetails)

        RecentMatchesContent(recentMatches = teamMatches)

        FeedContent(
            isNotLoggedIn  = userDetails == null,
            isTeamNotSelected = userDetails?.teamId == null,
            competition = teamMatches.first().competitionEntity
        )
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
                MatchEntity(
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
                        fullTime = ScoreEntity.TeamScore(
                            homeTeam = 1,
                            awayTeam = 1
                        ),
                        halfTime = ScoreEntity.TeamScore(
                            homeTeam = 0,
                            awayTeam = 0
                        ),
                        extraTime = ScoreEntity.TeamScore(
                            homeTeam = null,
                            awayTeam = null
                        ),
                        penalties = ScoreEntity.TeamScore(
                            homeTeam = null,
                            awayTeam = null
                        )
                    )
                ),
                MatchEntity(
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
                        fullTime = ScoreEntity.TeamScore(
                            homeTeam = 0,
                            awayTeam = 1
                        ),
                        halfTime = ScoreEntity.TeamScore(
                            homeTeam = 0,
                            awayTeam = 1
                        ),
                        extraTime = ScoreEntity.TeamScore(
                            homeTeam = null,
                            awayTeam = null
                        ),
                        penalties = ScoreEntity.TeamScore(
                            homeTeam = null,
                            awayTeam = null
                        )
                    )
                )
            )
        )
    }
}