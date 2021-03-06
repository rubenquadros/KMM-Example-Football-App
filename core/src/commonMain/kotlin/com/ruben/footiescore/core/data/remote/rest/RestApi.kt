package com.ruben.footiescore.core.data.remote.rest

import com.ruben.footiescore.core.data.remote.model.request.TeamRequest
import com.ruben.footiescore.shared.remote.model.ApiResponse
import com.ruben.footiescore.core.data.remote.model.request.LoginRequest
import com.ruben.footiescore.core.data.remote.model.request.SaveTeamRequest
import com.ruben.footiescore.core.data.remote.model.request.SearchRequest
import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.remote.model.response.GetLiveMatchesResponse
import com.ruben.footiescore.core.data.remote.model.response.GetUserTeamResponse
import com.ruben.footiescore.core.data.remote.model.response.UserResponse
import com.ruben.footiescore.core.data.remote.model.response.RecentMatchesResponse
import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface RestApi {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<UserResponse, JsonObject>
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject>
    suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, JsonObject>
    suspend fun saveTeam(saveTeamRequest: SaveTeamRequest): ApiResponse<Nothing, Nothing>
    suspend fun getRecentMatches(getRecentMatchesRequest: TeamRequest): ApiResponse<RecentMatchesResponse, JsonObject>
    suspend fun getUserTeam(getUserTeamRequest: TeamRequest): ApiResponse<GetUserTeamResponse, JsonObject>
    suspend fun getLiveMatches(): ApiResponse<GetLiveMatchesResponse, JsonObject>
}