package com.ruben.footiescore.core.data.remote.model.request

import kotlinx.serialization.Serializable

/**
 * Created by Ruben Quadros on 27/11/21
 **/
@Serializable
data class SearchRequest(
    val searchQuery: String
)
