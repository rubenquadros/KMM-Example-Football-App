package com.ruben.footiescore.core.domain.entity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
data class UserEntity(
    val id: String,
    val name: String,
    val email: String,
    val profilePic: String,
    val teamId: Int?
)
