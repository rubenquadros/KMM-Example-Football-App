package com.ruben.remote.rest

import com.ruben.remote.BuildKonfig
import com.ruben.remote.ktor.KtorService
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.request.SearchRequest
import com.ruben.remote.model.response.GetAllCompetitionsResponse
import com.ruben.remote.model.response.LoginResponse
import com.ruben.remote.model.response.SearchTeamResponse
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class RestApiImpl(private val ktorService: KtorService, private val baseUrl: String = BuildKonfig.BASE_URL): RestApi {
    override suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse, JsonObject> {
        return ktorService.client.post(urlString = "$baseUrl/login") {
            body = loginRequest
        }
    }

    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject> {
        return ktorService.client.get(urlString = "$baseUrl/competitions")
    }

    override suspend fun searchTeams(searchRequest: SearchRequest): ApiResponse<SearchTeamResponse, JsonObject> {
        return ktorService.client.get(urlString = "$baseUrl/search_teams") {
            parameter("search_query", searchRequest.searchQuery)
        }
    }
}