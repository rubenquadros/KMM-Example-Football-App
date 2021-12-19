package com.ruben.footiescore.core.data.repository

import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.remote.model.response.LoginResponse
import com.ruben.footiescore.core.data.remote.model.response.RecentMatchesResponse
import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface FootballRepository {
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject>
    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun setFirstTimeLaunch(firstTimeLaunch: Boolean)
    suspend fun login(id: String, name: String, email: String, image: String): ApiResponse<LoginResponse, JsonObject>
    suspend fun saveUserData(id: String, name: String, email: String, image: String, teamId: Int?)
    suspend fun searchTeam(searchQuery: String): ApiResponse<SearchTeamResponse, JsonObject>
    suspend fun getIsUserLoggedIn(): Boolean
    suspend fun setIsUserLoggedIn(isLogin: Boolean)
    suspend fun saveTeam(id: Int): ApiResponse<Nothing, Nothing>
    suspend fun getRecentMatches(): ApiResponse<RecentMatchesResponse, JsonObject>
}