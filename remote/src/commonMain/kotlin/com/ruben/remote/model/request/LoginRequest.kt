package com.ruben.remote.model.request

/**
 * Created by Ruben Quadros on 20/11/21
 **/
data class LoginRequest(
    val userId: String,
    val userName: String,
    val email: String,
    val profilePic: String
)
