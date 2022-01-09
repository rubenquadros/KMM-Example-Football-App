package com.ruben.footiescore.core.data.repository

import com.ruben.footiescore.core.data.remote.model.response.*
import com.ruben.footiescore.shared.remote.model.ApiResponse
import com.ruben.footiescore.shared.remote.model.DBResponse
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface FootballRepository {
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject>
    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun setFirstTimeLaunch(firstTimeLaunch: Boolean)
    suspend fun login(id: String, name: String, email: String, image: String): ApiResponse<UserResponse, JsonObject>
    suspend fun saveUserData(id: String, name: String, email: String, image: String, teamId: Int?)
    suspend fun searchTeam(searchQuery: String): ApiResponse<SearchTeamResponse, JsonObject>
    suspend fun getIsUserLoggedIn(): Boolean
    suspend fun setIsUserLoggedIn(isLogin: Boolean)
    suspend fun saveTeam(id: Int): ApiResponse<Nothing, Nothing>
    suspend fun getRecentMatches(): ApiResponse<RecentMatchesResponse, JsonObject>
    suspend fun getUserData(): DBResponse<UserResponse>
    suspend fun getUserProfile(): ApiResponse<UserProfileResponse, JsonObject>
    suspend fun getLiveMatches(): ApiResponse<GetLiveMatchesResponse, JsonObject>
}