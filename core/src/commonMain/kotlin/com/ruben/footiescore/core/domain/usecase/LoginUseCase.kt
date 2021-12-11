package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.LoginResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.UserEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.entity.mapErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 31/10/21
 **/
class LoginUseCase(private val repository: FootballRepository) :
    BaseUseCase<LoginUseCase.RequestValue, UserEntity, Nothing>() {

    override suspend fun execute(request: RequestValue): Flow<BaseEntity<UserEntity, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.login(request.id, request.name, request.email, request.image)) {
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

    data class RequestValue(val id: String, val name: String, val email: String, val image: String)
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