package com.ruben.footiescore.core.data.remote.rest

import com.ruben.footiescore.shared.remote.ktor.KtorService
import com.ruben.footiescore.shared.remote.model.ApiResponse
import com.ruben.footiescore.core.data.remote.model.request.LoginRequest
import com.ruben.footiescore.core.data.remote.model.request.SaveTeamRequest
import com.ruben.footiescore.core.data.remote.model.request.SearchRequest
import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.remote.model.response.LoginResponse
import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val ktorService: KtorService): RestApi {

    override suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse, JsonObject> {
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
}