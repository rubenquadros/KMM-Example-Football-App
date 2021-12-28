package com.ruben.footiescore.core.data.remote.model.response

/**
 * Created by Ruben Quadros on 26/12/21
 **/
data class UserProfileResponse(
    val userDetails: UserResponse,
    val teamDetails: GetUserTeamResponse? = null
)
