package com.ruben.data.mapper

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.remote.model.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
fun GetAllCompetitionsResponse.toUIEntity(): List<AllCompetitionEntity> {

    fun GetAllCompetitionsResponse.Competition.toEntity(): AllCompetitionEntity =
        AllCompetitionEntity(id = this.id, name = this.name, image = this.emblemUrl ?: "")

    return this.competitions.map {
        it.toEntity()
    }
}