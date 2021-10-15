package com.ruben.footiescore

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface FootballRepository {
    suspend fun getAllCompetitions(): BaseEntity<List<AllCompetitionEntity>, Nothing>
}