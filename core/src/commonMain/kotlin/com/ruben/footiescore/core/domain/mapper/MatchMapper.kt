package com.ruben.footiescore.core.domain.mapper

import com.ruben.footiescore.core.data.remote.model.response.MatchResponse
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity
import com.ruben.footiescore.core.domain.entity.MatchEntity
import com.ruben.footiescore.core.domain.entity.ScoreEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity

/**
 * Created by Ruben Quadros on 09/01/22
 **/
fun MatchResponse.toUIEntity(): MatchEntity {
    fun MatchResponse.Competition.Area.toUIEntity(): AreaEntity =
        AreaEntity(
            name = this.name,
            code = this.code,
            areaUrl = this.ensignUrl.orEmpty()
        )

    fun MatchResponse.Competition.toUIEntity(): CompetitionEntity =
        CompetitionEntity(
            id = this.id,
            name = this.name,
            area = this.area.toUIEntity()
        )

    fun MatchResponse.Team.toUIEntity(): TeamEntity =
        TeamEntity(
            id = this.id,
            name = this.name,
            shortName = this.shortName,
            crestUrl = this.crestUrl.orEmpty()
        )

    fun MatchResponse.Score.toUIEntity(): ScoreEntity =
        ScoreEntity(
            fullTime = ScoreEntity.TeamScore(
                homeTeam = this.fullTime.homeTeam,
                awayTeam = this.fullTime.awayTeam
            ),
            halfTime = ScoreEntity.TeamScore(
                homeTeam = this.halfTime.homeTeam,
                awayTeam = this.halfTime.awayTeam
            ),
            extraTime = ScoreEntity.TeamScore(
                homeTeam = this.extraTime.homeTeam,
                awayTeam = this.extraTime.awayTeam
            ),
            penalties = ScoreEntity.TeamScore(
                homeTeam = this.penalties.homeTeam,
                awayTeam = this.penalties.awayTeam
            )
        )

    return MatchEntity(
        id = this.id,
        date = this.utcDate.toDate(),
        competitionEntity = this.competition.toUIEntity(),
        homeTeam = this.homeTeam.toUIEntity(),
        awayTeam = this.awayTeam.toUIEntity(),
        scoreEntity = this.score.toUIEntity()
    )
}