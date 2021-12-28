package com.ruben.footiescore.core.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCompetitionsResponse(
    @SerialName("competitions")
    val competitions: List<Competition>,
    @SerialName("count")
    val count: Int
) {
    @Serializable
    data class Competition(
        @SerialName("area_crest")
        val areaCrest: String? = null,
        @SerialName("area_id")
        val areaId: String,
        @SerialName("area_name")
        val areaName: String,
        @SerialName("code")
        val code: String,
        @SerialName("competition_crest")
        val competitionCrest: String? = null,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    )
}