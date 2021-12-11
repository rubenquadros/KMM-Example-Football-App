package com.ruben.footiescore.core.domain.mapper

import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity
import com.ruben.footiescore.shared.remote.model.ApiResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
internal fun com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse.toUIEntity(): List<AllCompetitionEntity> {

    fun com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse.Competition.toEntity(): AllCompetitionEntity =
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

internal fun com.ruben.footiescore.core.data.remote.model.response.LoginResponse.toUIEntity(): UserEntity {
    return UserEntity(
        id = this.userId,
        name = this.name,
        email = this.email,
        profilePic = this.profilePic,
        teamId = this.teamId
    )
}

internal fun com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse.toUIEntity(): List<SearchTeamEntity> {
    fun com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse.Hit.toEntity(): SearchTeamEntity =
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