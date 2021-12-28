package com.ruben.footiescore.core.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserTeamResponse(
    @SerialName("area_id")
    val areaId: Int,
    @SerialName("area_name")
    val areaName: String,
    @SerialName("crest")
    val crest: String,
    @SerialName("id")
    val id: Int,
    @SerialName("initials")
    val initials: String,
    @SerialName("name")
    val name: String,
    @SerialName("short_name")
    val shortName: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("website")
    val website: String
)