package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.entity.mapErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 12/12/21
 **/
class SaveTeamUseCase(private val repository: FootballRepository): BaseUseCase<SaveTeamUseCase.RequestValue, Nothing, Nothing>() {

    override suspend fun execute(request: RequestValue): Flow<BaseEntity<Nothing, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.saveTeam(id = request.id)) {
            is ApiResponse.SuccessNoBody -> emit(BaseEntity.SuccessNoBody)
            is ApiResponse.DatabaseError -> emit(BaseEntity.ClientError)
            is ApiResponse.ErrorNoBody -> emit(mapErrorEntity(result.code))
            else -> emit(BaseEntity.UnknownError)
        }
    }

    data class RequestValue(val id: Int)
}