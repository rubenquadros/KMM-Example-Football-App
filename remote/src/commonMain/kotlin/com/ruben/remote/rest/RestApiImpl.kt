package com.ruben.remote.rest

import com.ruben.remote.ApiClient
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.response.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val apiClient: ApiClient): RestApi {
    override suspend fun login(loginRequest: LoginRequest): ApiResponse<Nothing, Nothing> {
        return apiClient.post(endPoint = "/login", requestBody = loginRequest)
    }

    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing> {
        return apiClient.get(endPoint = "/competitions")
    }
}