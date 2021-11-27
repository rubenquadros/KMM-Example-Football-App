package com.ruben.remote.algolia

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.SearchRequest

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class AlgoliaApiImpl: AlgoliaApi {
    override suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<Nothing, Nothing> {
        return ApiResponse.SuccessNoBody
    }
}