package com.ruben.footiescore.core.domain.entity

/**
 * Created by Ruben Quadros on 19/12/21
 **/
data class RecentMatchesEntity(
    val id: Int,
    val competitionEntity: CompetitionEntity,
    val date: String,
    val scoreEntity: ScoreEntity,
    val homeTeam: TeamEntity,
    val awayTeam: TeamEntity
)
