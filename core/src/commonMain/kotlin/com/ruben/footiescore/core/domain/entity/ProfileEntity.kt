package com.ruben.footiescore.core.domain.entity

/**
 * Created by Ruben Quadros on 26/12/21
 **/
data class ProfileEntity(
    val userEntity: UserEntity,
    val teamEntity: TeamEntity?
)
