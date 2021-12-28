package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.UserEntity
import com.ruben.footiescore.core.domain.mapper.toUIEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.DBResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 19/12/21
 **/
class GetUserDataUseCase(private val repository: FootballRepository): BaseUseCase<Unit, UserEntity, Nothing>() {

    override suspend fun execute(request: Unit): Flow<BaseEntity<UserEntity, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result =  repository.getUserData()) {
            is DBResponse.Success -> {
                emit(BaseEntity.Success(result.data.toUIEntity()))
            }
            else -> emit(BaseEntity.UnknownError)
        }
    }
}