package com.ruben.remote.rest

import com.ruben.remote.ktor.ApiClient
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.request.SearchRequest
import com.ruben.remote.model.response.GetAllCompetitionsResponse
import com.ruben.remote.model.response.LoginResponse
import com.ruben.remote.model.response.SearchTeamResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val apiClient: ApiClient): RestApi {
    override suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse, Nothing> {
        return apiClient.post(endPoint = "/login", requestBody = loginRequest)
    }

    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing> {
        return apiClient.get(endPoint = "/competitions")
    }

    override suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, Nothing> {
        return apiClient.get(endPoint = "/search_teams", params = mapOf("search_query" to searchRequest.searchQuery))
    }
}