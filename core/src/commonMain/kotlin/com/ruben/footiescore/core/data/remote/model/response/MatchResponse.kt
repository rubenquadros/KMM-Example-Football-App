package com.ruben.footiescore.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ruben Quadros on 09/01/22
 **/
@Serializable
data class MatchResponse(
    @SerialName("awayTeam")
    val awayTeam: Team,
    @SerialName("competition")
    val competition: Competition,
    @SerialName("group")
    val group: String? = null,
    @SerialName("homeTeam")
    val homeTeam: Team,
    @SerialName("id")
    val id: Int,
    @SerialName("lastUpdated")
    val lastUpdated: String,
    @SerialName("matchday")
    val matchDay: Int? = null,
    @SerialName("odds")
    val odds: Odds? = null,
    @SerialName("referees")
    val referees: List<Referee>,
    @SerialName("score")
    val score: Score,
    @SerialName("season")
    val season: Season,
    @SerialName("stage")
    val stage: String,
    @SerialName("status")
    val status: String,
    @SerialName("utcDate")
    val utcDate: String
) {
    @Serializable
    data class Competition(
        @SerialName("area")
        val area: Area,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    ) {
        @Serializable
        data class Area(
            @SerialName("code")
            val code: String,
            @SerialName("ensignUrl")
            val ensignUrl: String? = null,
            @SerialName("name")
            val name: String
        )
    }

    @Serializable
    data class Odds(
        @SerialName("msg")
        val msg: String
    )

    @Serializable
    data class Referee(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("nationality")
        val nationality: String? = null,
        @SerialName("role")
        val role: String
    )

    @Serializable
    data class Score(
        @SerialName("duration")
        val duration: String,
        @SerialName("extraTime")
        val extraTime: ExtraTime,
        @SerialName("fullTime")
        val fullTime: FullTime,
        @SerialName("halfTime")
        val halfTime: HalfTime,
        @SerialName("penalties")
        val penalties: Penalties,
        @SerialName("winner")
        val winner: String
    ) {
        @Serializable
        data class ExtraTime(
            @SerialName("awayTeam")
            val awayTeam: Int? = null,
            @SerialName("homeTeam")
            val homeTeam: Int? = null
        ) {
            fun isOngoing() = this.awayTeam != null && this.homeTeam != null
        }

        @Serializable
        data class FullTime(
            @SerialName("awayTeam")
            val awayTeam: Int? = null,
            @SerialName("homeTeam")
            val homeTeam: Int? = null
        ) {
            fun isOngoing() = this.awayTeam != null && this.homeTeam != null
        }

        @Serializable
        data class HalfTime(
            @SerialName("awayTeam")
            val awayTeam: Int? = null,
            @SerialName("homeTeam")
            val homeTeam: Int? = null
        ) {
            fun isOngoing() = this.awayTeam != null && this.homeTeam != null
        }

        @Serializable
        data class Penalties(
            @SerialName("awayTeam")
            val awayTeam: Int? = null,
            @SerialName("homeTeam")
            val homeTeam: Int? = null
        ) {
            fun isOngoing() = this.awayTeam != null && this.homeTeam != null
        }
    }

    @Serializable
    data class Season(
        @SerialName("currentMatchday")
        val currentMatchDay: Int,
        @SerialName("endDate")
        val endDate: String,
        @SerialName("id")
        val id: Int,
        @SerialName("startDate")
        val startDate: String,
        @SerialName("winner")
        val winner: String? = null
    )

    @Serializable
    data class Team(
        @SerialName("crest_url")
        val crestUrl: String? = null,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("short_name")
        val shortName: String
    )
}
