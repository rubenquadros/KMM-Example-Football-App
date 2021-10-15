package com.ruben.remote.rest

import com.ruben.remote.ApiClient
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val apiClient: ApiClient): RestApi {
    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing> {
        return apiClient.get(endPoint = "/competitions")
    }
}