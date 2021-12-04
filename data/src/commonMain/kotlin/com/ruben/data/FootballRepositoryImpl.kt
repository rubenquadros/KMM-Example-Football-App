package com.ruben.data

import com.ruben.data.mapper.mapErrorEntity
import com.ruben.data.mapper.toUIEntity
import com.ruben.footiescore.FootballRepository
import com.ruben.footiescore.dispatcher.DispatcherProvider
import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.SearchTeamEntity
import com.ruben.footiescore.entity.UserEntity
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.request.SearchRequest
import kotlinx.coroutines.withContext

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class FootballRepositoryImpl(private val dataSource: DataSource, private val dispatcherProvider: DispatcherProvider): FootballRepository {

    override suspend fun getAllCompetitions(): BaseEntity<List<AllCompetitionEntity>, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            when (val response = dataSource.api().restApi().getAllCompetitions()) {
                is ApiResponse.Success -> {
                    BaseEntity.Success(response.body.toUIEntity())
                }
                else -> BaseEntity.UnknownError
            }
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

    override suspend fun login(id: String, name: String, email: String, image: String): BaseEntity<UserEntity, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            val response = dataSource.api().restApi().login(
                loginRequest = LoginRequest(
                    userId = id,
                    userName = name,
                    email = email,
                    profilePic = image
                )
            )
            when (response) {
                is ApiResponse.Success -> {
                    val userData = response.body
                    saveUserData(userData.userId, userData.name, userData.email, userData.profilePic, userData.teamId)
                    BaseEntity.Success(userData.toUIEntity())
                }
                is ApiResponse.ErrorNoBody -> {
                    mapErrorEntity(response.code)
                }
                else -> {
                    BaseEntity.UnknownError
                }
            }
        }
    }

    override suspend fun saveUserData(id: String, name: String, email: String, image: String, teamId: Int?) {
        withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.database().userQueries.insertUser(id, name, email, image, teamId?.toLong())
        }
    }

    override suspend fun searchTeam(searchQuery: String): BaseEntity<List<SearchTeamEntity>, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            val response = dataSource.api().restApi().searchTeams(
                searchRequest = SearchRequest(searchQuery = searchQuery)
            )
            when (response) {
                is ApiResponse.Success -> {
                    BaseEntity.Success(response.body.toUIEntity())
                }
                is ApiResponse.ErrorNoBody -> {
                    mapErrorEntity(response.code)
                }
                else -> BaseEntity.UnknownError
            }
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
}