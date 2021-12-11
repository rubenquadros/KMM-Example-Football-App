package com.ruben.footiescore.core.data.repository

import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity

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