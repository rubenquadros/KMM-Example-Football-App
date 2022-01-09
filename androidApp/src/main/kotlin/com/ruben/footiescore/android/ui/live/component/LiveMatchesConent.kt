package com.ruben.footiescore.android.ui.live.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.MatchEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity

/**
 * Created by Ruben Quadros on 09/01/22
 **/
@Composable
fun AnimatedVisibilityScope.LiveMatchesContent(modifier: Modifier = Modifier, matches: List<MatchEntity>) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = matches,
            key = { _, item -> item.id }
        ) { index, match ->
            LiveMatchRow(index = index, match = match)
            Divider(
                color = FootieScoreTheme.colors.disabled,
                thickness = 1.dp
            )
        }
    }
}

@Preview
@Composable
fun PreviewLiveMatchesContent() {
    AnimatedVisibility(visible = true) {
        LiveMatchesContent(
            matches = listOf(
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
                    id = 6,
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
                    id = 7,
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
                    id = 8,
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
        )
    }
}