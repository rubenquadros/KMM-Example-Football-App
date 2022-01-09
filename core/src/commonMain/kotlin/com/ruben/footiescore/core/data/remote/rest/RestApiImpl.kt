package com.ruben.footiescore.core.data.remote.rest

import com.ruben.footiescore.core.data.remote.model.request.TeamRequest
import com.ruben.footiescore.shared.remote.ktor.KtorService
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
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val ktorService: KtorService): RestApi {

    override suspend fun login(loginRequest: LoginRequest): ApiResponse<UserResponse, JsonObject> {
        return ktorService.client.post(path = "login") {
            body = loginRequest
        }
    }

    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject> {
        return ktorService.client.get(path = "competitions")
    }

    override suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, JsonObject> {
        return ktorService.client.get(path = "search_teams") {
            parameter("search_query", searchRequest.searchQuery)
        }
    }

    override suspend fun saveTeam(saveTeamRequest: SaveTeamRequest): ApiResponse<Nothing, Nothing> {
        return ktorService.client.post(path = "save_team") {
            body = saveTeamRequest
        }
    }

    override suspend fun getRecentMatches(getRecentMatchesRequest: TeamRequest): ApiResponse<RecentMatchesResponse, JsonObject> {
        return ktorService.client.get(path = "recent_matches") {
            parameter("team_id", getRecentMatchesRequest.teamId)
        }
    }

    override suspend fun getUserTeam(getUserTeamRequest: TeamRequest): ApiResponse<GetUserTeamResponse, JsonObject> {
        return ktorService.client.get(path = "get_user_team") {
            parameter("team_id", getUserTeamRequest.teamId)
        }
    }

    override suspend fun getLiveMatches(): ApiResponse<GetLiveMatchesResponse, JsonObject> {
        return ktorService.client.get(path = "live_matches")
    }
}