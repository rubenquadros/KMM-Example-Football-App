package com.ruben.data.mapper

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.remote.model.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
internal fun GetAllCompetitionsResponse.toUIEntity(): List<AllCompetitionEntity> {

    fun GetAllCompetitionsResponse.Competition.toEntity(): AllCompetitionEntity =
        AllCompetitionEntity(
            id = this.id,
            name = this.name,
            image = this.emblemUrl ?: this.area.ensignUrl ?: this.currentSeason.winner?.crestUrl ?: ""
        )

    return this.competitions.map {
        it.toEntity()
    }
}