package com.ruben.data

import com.ruben.data.mapper.toUIEntity
import com.ruben.footiescore.FootballRepository
import com.ruben.footiescore.dispatcher.DispatcherProvider
import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.remote.model.ApiResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class FootballRepositoryImpl(private val dataSource: DataSource, private val dispatcherProvider: DispatcherProvider): FootballRepository {

    override suspend fun getAllCompetitions(): BaseEntity<List<AllCompetitionEntity>, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            when (val response = dataSource.api().getAllCompetitions()) {
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

    override suspend fun login(id: String, name: String, email: String, image: String): BaseEntity<Nothing, Nothing> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            saveUserData(id, name, email, image)
            BaseEntity.SuccessNoBody
        }
    }

    override suspend fun saveUserData(id: String, name: String, email: String, image: String) {
        withContext(dispatcherProvider.dispatcherDefault) {
            dataSource.database().userQueries.insertUser(id, name, email, image)
        }
    }
}