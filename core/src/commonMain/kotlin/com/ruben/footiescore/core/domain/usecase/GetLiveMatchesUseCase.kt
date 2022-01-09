package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.GetLiveMatchesResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.MatchEntity
import com.ruben.footiescore.core.domain.mapper.toUIEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.entity.mapErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 09/01/22
 **/
class GetLiveMatchesUseCase(private val repository: FootballRepository): BaseUseCase<Unit, List<MatchEntity>, Nothing>() {

    override suspend fun execute(request: Unit): Flow<BaseEntity<List<MatchEntity>, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.getLiveMatches()) {
            is ApiResponse.Success -> {
                emit(BaseEntity.Success(result.body.toUIEntity()))
            }
            is ApiResponse.ErrorNoBody -> {
                emit(mapErrorEntity(result.code))
            }
            else -> {
                emit(BaseEntity.UnknownError)
            }
        }
    }
}

internal fun GetLiveMatchesResponse.toUIEntity(): List<MatchEntity> {
    return this.matches.map {
        it.toUIEntity()
    }
}