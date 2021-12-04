package com.ruben.footiescore

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.SearchTeamEntity
import com.ruben.footiescore.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface FootballRepository {
    suspend fun getAllCompetitions(): BaseEntity<List<AllCompetitionEntity>, Nothing>
    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun setFirstTimeLaunch(firstTimeLaunch: Boolean)
    suspend fun login(id: String, name: String, email: String, image: String): BaseEntity<UserEntity, Nothing>
    suspend fun saveUserData(id: String, name: String, email: String, image: String, teamId: Int?)
    suspend fun searchTeam(searchQuery: String): BaseEntity<List<SearchTeamEntity>, Nothing>
    suspend fun getIsUserLoggedIn(): Boolean
    suspend fun setIsUserLoggedIn(isLogin: Boolean)
}