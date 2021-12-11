package com.ruben.footiescore.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ruben Quadros on 20/11/21
 **/
@Serializable
data class LoginRequest(
    @SerialName("id")
    val userId: String,
    @SerialName("name")
    val userName: String,
    @SerialName("email")
    val email: String,
    @SerialName("profile_pic")
    val profilePic: String
)
