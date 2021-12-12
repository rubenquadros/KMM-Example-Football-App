package com.ruben.footiescore.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ruben Quadros on 12/12/21
 **/
@Serializable
data class SaveTeamRequest(
    @SerialName("id")
    val teamId: Int,
    @SerialName("user_id")
    val userId: String
)
