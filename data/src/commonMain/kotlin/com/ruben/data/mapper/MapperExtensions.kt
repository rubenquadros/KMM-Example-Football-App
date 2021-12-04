package com.ruben.data.mapper

import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.SearchTeamEntity
import com.ruben.footiescore.entity.UserEntity
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.response.GetAllCompetitionsResponse
import com.ruben.remote.model.response.LoginResponse
import com.ruben.remote.model.response.SearchTeamResponse

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
    return when (this) {
        is ApiResponse.SuccessNoBody -> BaseEntity.SuccessNoBody
        is ApiResponse.ErrorNoBody ->  mapErrorEntity(this.code)
        else -> BaseEntity.UnknownError
    }
}

internal fun LoginResponse.toUIEntity(): UserEntity {
    return UserEntity(
        id = this.userId,
        name = this.name,
        email = this.email,
        profilePic = this.profilePic,
        teamId = this.teamId
    )
}

internal fun SearchTeamResponse.toUIEntity(): List<SearchTeamEntity> {
    fun SearchTeamResponse.Hit.toEntity(): SearchTeamEntity =
        SearchTeamEntity(
            id = this.id,
            name = this.name,
            area = this.area,
            image = this.crestUrl
        )

    return this.hits.map { it.toEntity() }
}

fun <T>mapErrorEntity(code: Int): BaseEntity<T, Nothing> {
    return when (code) {
        in (400..499) -> BaseEntity.ClientError
        in (500..599) -> BaseEntity.ServerError
        else -> BaseEntity.UnknownError
    }
}