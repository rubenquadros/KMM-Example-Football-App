package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.GetAllCompetitionsResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity
import com.ruben.footiescore.core.domain.entity.ErrorEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class GetAllCompetitionsUseCase(private val repository: FootballRepository) :
    BaseUseCase<Unit, List<AllCompetitionEntity>, ErrorEntity>() {

    override suspend fun execute(request: Unit): Flow<BaseEntity<List<AllCompetitionEntity>, ErrorEntity>> = flow {
        emit(BaseEntity.Loading)
        when (val result: ApiResponse<GetAllCompetitionsResponse, JsonObject> = repository.getAllCompetitions()) {
            is ApiResponse.Success -> {
                emit(BaseEntity.Success(result.body.toUIEntity()))
            }
            else -> emit(BaseEntity.UnknownError)
        }
    }
}

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