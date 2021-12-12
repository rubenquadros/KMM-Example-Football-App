package com.ruben.footiescore.core.data.repository

import com.ruben.footiescore.core.data.DataSource
import com.ruben.footiescore.core.data.remote.model.request.LoginRequest
import com.ruben.footiescore.core.data.remote.model.request.SaveTeamRequest
import com.ruben.footiescore.core.data.remote.model.request.SearchRequest
import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.remote.model.response.LoginResponse
import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import com.ruben.footiescore.shared.domain.dispatcher.DispatcherProvider
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class FootballRepositoryImpl(private val dataSource: DataSource, private val dispatcherProvider: DispatcherProvider):
    FootballRepository {

    override suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, JsonObject> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.api().restApi().getAllCompetitions()
        }
    }

    override suspend fun getIsFirstTimeLaunch(): Boolean {
        return withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.appStorage().getIsFirstTimeLaunch()
        }
    }

    override suspend fun setFirstTimeLaunch(firstTimeLaunch: Boolean) {
        withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.appStorage().storeFirstTime(firstTimeLaunch)
        }
    }

    override suspend fun login(id: String, name: String, email: String, image: String): ApiResponse<LoginResponse, JsonObject> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            val response = dataSource.api().restApi().login(
                loginRequest = LoginRequest(
                    userId = id,
                    userName = name,
                    email = email,
                    profilePic = image
                )
            )
            if (response is ApiResponse.Success) {
                val userData = response.body
                saveUserData(userData.userId, userData.name, userData.email, userData.profilePic, userData.teamId)
            }
            response
        }
    }

    override suspend fun saveUserData(id: String, name: String, email: String, image: String, teamId: Int?) {
        withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.database().userQueries.insertUser(id, name, email, image, teamId?.toLong())
        }
    }

    override suspend fun searchTeam(searchQuery: String): ApiResponse<SearchTeamResponse, JsonObject> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.api().restApi().searchTeams(
                searchRequest = SearchRequest(searchQuery = searchQuery)
            )
        }
    }

    override suspend fun getIsUserLoggedIn(): Boolean {
        return withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.appStorage().isUserLoggedIn()
        }
    }

    override suspend fun setIsUserLoggedIn(isLogin: Boolean) {
        withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.appStorage().setUserLogin(isLogin)
        }
    }

    override suspend fun saveTeam(id: Int): ApiResponse<Nothing, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            val userId = runCatching {
                dataSource.database().userQueries.getUserId().executeAsOne()
            }.getOrNull()
            if (userId.isNullOrBlank()) {
                ApiResponse.DatabaseError
            } else {
                val response = dataSource.api().restApi().saveTeam(
                    saveTeamRequest = SaveTeamRequest(userId = userId, teamId = id)
                )
                if (response is ApiResponse.SuccessNoBody) {
                    dataSource.database().userQueries.updateTeam(id.toLong(), userId)
                }
                response
            }
        }
    }
}