package com.ruben.footiescore.core.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentMatchesResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("filters")
    val filters: Filters,
    @SerialName("matches")
    val matches: List<MatchResponse>
) {
    @Serializable
    data class Filters(
        @SerialName("limit")
        val limit: Int,
        @SerialName("permission")
        val permission: String,
        @SerialName("status")
        val status: List<String>
    )
}