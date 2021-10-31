package com.ruben.footiescore.usecase

import com.ruben.footiescore.FootballRepository
import com.ruben.footiescore.entity.BaseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 31/10/21
 **/
class LoginUseCase(private val repository: FootballRepository) :
    BaseUseCase<LoginUseCase.RequestValue, Nothing, Nothing>() {

    override suspend fun execute(request: RequestValue): Flow<BaseEntity<Nothing, Nothing>> = flow {
        emit(BaseEntity.Loading)
        val result = repository.login(request.id, request.name, request.email, request.image)
        emit(result)
    }

    data class RequestValue(val id: String, val name: String, val email: String, val image: String)
}