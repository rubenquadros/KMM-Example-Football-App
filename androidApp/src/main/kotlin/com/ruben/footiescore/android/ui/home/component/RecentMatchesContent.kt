package com.ruben.footiescore.android.ui.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.RecentMatchesEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity

/**
 * Created by Ruben Quadros on 19/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.RecentMatchesContent(
    modifier: Modifier = Modifier,
    recentMatches: List<RecentMatchesEntity>
) {
    val density = LocalDensity.current

    Column(
        modifier = modifier.animateEnterExit(
            enter = slideInHorizontallyAnim(
                offset = with(density) { 100.dp.roundToPx() },
                duration = 800
            ) + fadeInAnim(),
            exit = slideOutHorizontallyAnim(
                offset = with(density) { -100.dp.roundToPx() },
                duration = 400
            ) + fadeOutAnim()
        )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.home_dashboard_recent_matches),
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Card(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            shape = FootieScoreTheme.shapes.largeRoundCornerShape,
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Gray100
                    )
            ) {
                recentMatches.forEachIndexed { index, recentMatchesEntity ->
                    RecentMatchItem(recentMatchesEntity = recentMatchesEntity)
                    if (index != recentMatches.size - 1) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 18.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = FootieScoreTheme.colors.onSurface)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecentMatchItem(recentMatchesEntity: RecentMatchesEntity) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = ConstraintSet {
            val competitionName = createRefFor("competition_name")
            val homeCrest = createRefFor("home_crest")
            val awayCrest = createRefFor("away_crest")
            val homeTeamName = createRefFor("home_team_name")
            val awayTeamName = createRefFor("away_team_name")
            val scoreDateContainer = createRefFor("scores_date")

            constrain(competitionName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }

            constrain(homeCrest) {
                top.linkTo(competitionName.bottom)
                start.linkTo(parent.start, margin = 16.dp)
                height = Dimension.value(60.dp)
                width = Dimension.value(60.dp)
            }

            constrain(homeTeamName) {
                top.linkTo(homeCrest.bottom)
                start.linkTo(homeCrest.start)
                end.linkTo(homeCrest.end)
                width = Dimension.fillToConstraints
            }

            constrain(awayCrest) {
                top.linkTo(competitionName.bottom)
                end.linkTo(parent.end, margin = 16.dp)
                height = Dimension.value(60.dp)
                width = Dimension.value(60.dp)
            }

            constrain(awayTeamName) {
                top.linkTo(awayCrest.bottom)
                start.linkTo(awayCrest.start)
                end.linkTo(awayCrest.end)
                width = Dimension.fillToConstraints
            }

            constrain(scoreDateContainer) {
                top.linkTo(homeCrest.top)
                bottom.linkTo(homeCrest.bottom)
                start.linkTo(homeCrest.end)
                end.linkTo(awayCrest.start)
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .layoutId("competition_name"),
            text = recentMatchesEntity.competitionEntity.name,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Image(
            modifier = Modifier
                .clip(CircleShape)
                .layoutId("home_crest")
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,
            painter = if (recentMatchesEntity.homeTeam.crestUrl.isEmpty()) {
                painterResource(id = R.drawable.ic_football_black)
            } else {
                rememberImagePainter(
                    data = recentMatchesEntity.homeTeam.crestUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_football_black)
                        crossfade(true)
                    }
                )
            },
            contentDescription = stringResource(id = R.string.content_description_team_crest)
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .layoutId("home_team_name"),
            text = recentMatchesEntity.homeTeam.shortName,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Image(
            modifier = Modifier
                .clip(CircleShape)
                .layoutId("away_crest")
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,
            painter = if (recentMatchesEntity.awayTeam.crestUrl.isEmpty()) {
                painterResource(id = R.drawable.ic_football_black)
            } else {
                rememberImagePainter(
                    data = recentMatchesEntity.awayTeam.crestUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_football_black)
                        crossfade(true)
                    }
                )
            },
            contentDescription = stringResource(id = R.string.content_description_team_crest)
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .layoutId("away_team_name"),
            text = recentMatchesEntity.awayTeam.shortName,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Column(modifier = Modifier.layoutId("scores_date")) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${recentMatchesEntity.scoreEntity.homeTeam}-${recentMatchesEntity.scoreEntity.awayTeam}",
                style = FootieScoreTheme.typography.title1.copy(fontSize = 28.sp),
                color = FootieScoreTheme.colors.surface
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                text = recentMatchesEntity.date,
                style = FootieScoreTheme.typography.body2,
                color = FootieScoreTheme.colors.surface
            )
        }
    }
}

@Preview
@Composable
fun PreviewRecentMatchItem() {
    RecentMatchItem(
        recentMatchesEntity = RecentMatchesEntity(
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
        )
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewRecentMatchesContent() {
    AnimatedVisibility(visible = true) {
        RecentMatchesContent(
            modifier = Modifier.background(color = FootieScoreTheme.colors.onPrimary),
            recentMatches = listOf(
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