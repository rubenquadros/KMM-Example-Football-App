package com.ruben.footiescore.core.data.remote.rest

import com.ruben.footiescore.shared.remote.model.ApiResponse
import com.ruben.footiescore.core.data.remote.model.request.LoginRequest
import com.ruben.footiescore.core.data.remote.model.request.SearchRequest
import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.remote.model.response.LoginResponse
import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface RestApi {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse, JsonObject>
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject>
    suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, JsonObject>
}