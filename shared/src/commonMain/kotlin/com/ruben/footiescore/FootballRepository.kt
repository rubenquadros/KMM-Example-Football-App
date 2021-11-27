package com.ruben.footiescore

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.SearchTeamEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface FootballRepository {
    suspend fun getAllCompetitions(): BaseEntity<List<AllCompetitionEntity>, Nothing>
    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun setFirstTimeLaunch(firstTimeLaunch: Boolean)
    suspend fun login(id: String, name: String, email: String, image: String): BaseEntity<Nothing, Nothing>
    suspend fun saveUserData(id: String, name: String, email: String, image: String)
    suspend fun searchTeam(searchQuery: String): BaseEntity<SearchTeamEntity, Nothing>
}