package com.ruben.data.mapper

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.response.GetAllCompetitionsResponse

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

internal fun ApiResponse<Nothing, Nothing>.toUIEntity(): BaseEntity<Nothing, Nothing> {
    fun mapErrorEntity(code: Int): BaseEntity<Nothing, Nothing> {
        return when (code) {
            in (400..499) -> BaseEntity.ClientError
            in (500..599) -> BaseEntity.ServerError
            else -> BaseEntity.UnknownError
        }
    }

    return when (this) {
        is ApiResponse.SuccessNoBody -> BaseEntity.SuccessNoBody
        is ApiResponse.ErrorNoBody ->  mapErrorEntity(this.code)
        else -> BaseEntity.UnknownError
    }
}