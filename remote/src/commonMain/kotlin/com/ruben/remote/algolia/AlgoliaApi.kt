package com.ruben.remote.algolia

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.SearchRequest

/**
 * Created by Ruben Quadros on 27/11/21
 **/
interface AlgoliaApi {
    suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<Nothing, Nothing>
}