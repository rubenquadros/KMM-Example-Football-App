package com.ruben.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCompetitionsResponse(
    @SerialName("competitions")
    val competitions: List<Competition>,
    @SerialName("count")
    val count: Int,
    @SerialName("filters")
    val filters: Filters
) {
    @Serializable
    data class Competition(
        @SerialName("area")
        val area: Area,
        @SerialName("code")
        val code: String,
        @SerialName("currentSeason")
        val currentSeason: CurrentSeason,
        @SerialName("emblemUrl")
        val emblemUrl: String?,
        @SerialName("id")
        val id: Int,
        @SerialName("lastUpdated")
        val lastUpdated: String,
        @SerialName("name")
        val name: String,
        @SerialName("numberOfAvailableSeasons")
        val numberOfAvailableSeasons: Int,
        @SerialName("plan")
        val plan: String
    ) {
        @Serializable
        data class Area(
            @SerialName("countryCode")
            val countryCode: String,
            @SerialName("ensignUrl")
            val ensignUrl: String?,
            @SerialName("id")
            val id: Int,
            @SerialName("name")
            val name: String
        )

        @Serializable
        data class CurrentSeason(
            @SerialName("currentMatchday")
            val currentMatchDay: Int,
            @SerialName("endDate")
            val endDate: String,
            @SerialName("id")
            val id: Int,
            @SerialName("startDate")
            val startDate: String,
            @SerialName("winner")
            val winner: Winner?
        ) {
            @Serializable
            data class Winner(
                @SerialName("crestUrl")
                val crestUrl: String,
                @SerialName("id")
                val id: Int,
                @SerialName("name")
                val name: String,
                @SerialName("shortName")
                val shortName: String,
                @SerialName("tla")
                val tla: String
            )
        }
    }

    @Serializable
    data class Filters(
        @SerialName("plan")
        val plan: String
    )
}