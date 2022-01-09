package com.ruben.footiescore.core.domain.entity

import com.ruben.footiescore.core.domain.mapper.toUIEntity

/**
 * Created by Ruben Quadros on 19/12/21
 **/
data class ScoreEntity(
    val fullTime: TeamScore,
    val halfTime: TeamScore,
    val extraTime: TeamScore,
    val penalties: TeamScore
) {
    data class TeamScore(
        val homeTeam: Int?,
        val awayTeam: Int?
    ) {
        fun isOngoing(): Boolean = homeTeam != null && awayTeam != null
    }

    fun getScore(): TeamScore {
        return when {
            penalties.isOngoing() || extraTime.isOngoing() -> {
                TeamScore(homeTeam = extraTime.homeTeam, awayTeam = extraTime.awayTeam)
            }
            fullTime.isOngoing() -> TeamScore(homeTeam = fullTime.homeTeam, awayTeam = fullTime.awayTeam)
            else -> TeamScore(homeTeam = halfTime.homeTeam, awayTeam = halfTime.awayTeam)
        }
    }

    fun getMatchTime(): String {
        return when {
            penalties.isOngoing() -> "Penalties"
            extraTime.isOngoing() -> "Extra Time"
            fullTime.isOngoing() -> "Second Half"
            else -> "First Half"
        }
    }
}
