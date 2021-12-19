package com.ruben.footiescore.core.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("profile_pic")
    val profilePic: String,
    @SerialName("team_id")
    val teamId: Int? = null,
    @SerialName("user_id")
    val userId: String
)