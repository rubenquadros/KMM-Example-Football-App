package com.ruben.footiescore.core.domain.mapper

import com.ruben.footiescore.core.data.remote.model.response.UserResponse
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 19/12/21
 **/
fun UserResponse.toUIEntity(): UserEntity {
    return UserEntity(
        id = this.userId,
        name = this.name,
        email = this.email,
        profilePic = this.profilePic,
        teamId = this.teamId
    )
}