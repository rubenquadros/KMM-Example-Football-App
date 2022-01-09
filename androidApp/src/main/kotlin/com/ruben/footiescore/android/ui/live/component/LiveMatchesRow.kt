package com.ruben.footiescore.android.ui.live.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.MatchEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity

/**
 * Created by Ruben Quadros on 09/01/22
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LiveMatchRow(index: Int, match: MatchEntity) {
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = enterAnim,
                exit = exitAnim
            ),
        constraintSet = ConstraintSet {
            val matchTime = createRefFor("match_time")
            val homeCrest = createRefFor("home_crest")
            val awayCrest = createRefFor("away_crest")
            val homeTeam = createRefFor("home_team")
            val awayTeam = createRefFor("away_team")
            val homeScore = createRefFor("home_score")
            val awayScore = createRefFor("away_score")
            val competitionName = createRefFor("competition_name")

            createVerticalChain(
                elements = arrayOf(homeCrest, awayCrest),
                chainStyle = ChainStyle.Spread
            )

            constrain(matchTime) {
                top.linkTo(parent.top)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom)
            }

            constrain(homeCrest) {
                start.linkTo(parent.start, margin = 8.dp)
                centerVerticallyTo(parent)
                width = Dimension.value(30.dp)
                height = Dimension.value(30.dp)
            }

            constrain(awayCrest) {
                start.linkTo(parent.start, margin = 8.dp)
                bottom.linkTo(competitionName.top, margin = 8.dp)
                centerVerticallyTo(parent)
                width = Dimension.value(30.dp)
                height = Dimension.value(30.dp)
            }

            constrain(homeTeam) {
                top.linkTo(homeCrest.top)
                start.linkTo(homeCrest.end, margin = 8.dp)
                end.linkTo(homeScore.start, margin = 8.dp)
                bottom.linkTo(homeCrest.bottom)
                width = Dimension.fillToConstraints
            }

            constrain(awayTeam) {
                top.linkTo(awayCrest.top)
                start.linkTo(awayCrest.end, margin = 8.dp)
                end.linkTo(awayScore.start, margin = 8.dp)
                bottom.linkTo(awayCrest.bottom)
                width = Dimension.fillToConstraints
            }

            constrain(homeScore) {
                top.linkTo(homeTeam.top)
                end.linkTo(matchTime.start, margin = 16.dp)
                bottom.linkTo(homeTeam.bottom)
            }

            constrain(awayScore) {
                top.linkTo(awayTeam.top)
                end.linkTo(matchTime.start, margin = 16.dp)
                bottom.linkTo(awayTeam.bottom)
            }

            constrain(competitionName) {
                top.linkTo(awayCrest.bottom)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    ) {
        Text(
            modifier = Modifier.layoutId("match_time"),
            text = match.scoreEntity.getMatchTime(),
            style = FootieScoreTheme.typography.caption,
            color = FootieScoreTheme.colors.onSurface
        )

        Image(
            modifier = Modifier
                .layoutId("home_crest")
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,
            painter = if (match.homeTeam.crestUrl.isEmpty()) {
                painterResource(id = R.drawable.ic_football_black)
            } else {
                rememberImagePainter(
                    data = match.homeTeam.crestUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_football_black)
                        crossfade(true)
                    }
                )
            },
            contentDescription = stringResource(id = R.string.content_description_team_crest)
        )

        Image(
            modifier = Modifier
                .layoutId("away_crest")
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,
            painter = if (match.awayTeam.crestUrl.isEmpty()) {
                painterResource(id = R.drawable.ic_football_black)
            } else {
                rememberImagePainter(
                    data = match.awayTeam.crestUrl,
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
            modifier = Modifier.layoutId("home_team"),
            text = match.homeTeam.shortName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FootieScoreTheme.typography.body1,
            color = FootieScoreTheme.colors.surface
        )

        Text(
            modifier = Modifier.layoutId("away_team"),
            text = match.awayTeam.shortName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FootieScoreTheme.typography.body1,
            color = FootieScoreTheme.colors.surface
        )

        Text(
            modifier = Modifier.layoutId("home_score"),
            text = match.scoreEntity.getScore().homeTeam.toString(),
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface
        )

        Text(
            modifier = Modifier.layoutId("away_score"),
            text = match.scoreEntity.getScore().awayTeam.toString(),
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface
        )

        Text(
            modifier = Modifier.layoutId("competition_name"),
            text = match.competitionEntity.name,
            style = FootieScoreTheme.typography.body2,
            color = FootieScoreTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
fun PreviewLiveMatchRow(@PreviewParameter(MatchEntityProvider::class) match: MatchEntity) {
    AnimatedVisibility(visible = true) {
        LiveMatchRow(
            index = 0,
            match = match
        )
    }
}

class MatchEntityProvider: PreviewParameterProvider<MatchEntity> {
    override val values: Sequence<MatchEntity> =
        sequenceOf(
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
                        homeTeam = null,
                        awayTeam = null
                    ),
                    halfTime = ScoreEntity.TeamScore(
                        homeTeam = 1,
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
                        homeTeam = 1,
                        awayTeam = 1
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
                        homeTeam = 1,
                        awayTeam = 1
                    ),
                    penalties = ScoreEntity.TeamScore(
                        homeTeam = 5,
                        awayTeam = 3
                    )
                )
            )
        )
}