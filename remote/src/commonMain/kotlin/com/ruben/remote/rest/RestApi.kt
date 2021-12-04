package com.ruben.remote.rest

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.request.SearchRequest
import com.ruben.remote.model.response.GetAllCompetitionsResponse
import com.ruben.remote.model.response.LoginResponse
import com.ruben.remote.model.response.SearchTeamResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface RestApi {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse, Nothing>
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing>
    suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, Nothing>
}