package com.ruben.footiescore.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ruben Quadros on 09/01/22
 **/
@Serializable
data class GetLiveMatchesResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("matches")
    val matches: List<MatchResponse>
)
