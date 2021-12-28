package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.GetUserTeamResponse
import com.ruben.footiescore.core.data.remote.model.response.UserProfileResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.ProfileEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity
import com.ruben.footiescore.core.domain.mapper.toUIEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 26/12/21
 **/
class GetUserProfileUseCase(private val repository: FootballRepository): BaseUseCase<Unit, ProfileEntity, Nothing>() {

    override suspend fun execute(request: Unit): Flow<BaseEntity<ProfileEntity, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.getUserProfile()) {
            is ApiResponse.Success -> {
                emit(BaseEntity.Success(result.body.toUIEntity()))
            }
            else -> {
                emit(BaseEntity.UnknownError)
            }
        }
    }
}

internal fun UserProfileResponse.toUIEntity(): ProfileEntity {
    fun GetUserTeamResponse?.toUIEntity(): TeamEntity? {
        return if (this == null) null
        else TeamEntity(
            id = this.id,
            name = this.name,
            crestUrl = this.crest,
            shortName = this.shortName
        )
    }

    return ProfileEntity(
        userEntity = this.userDetails.toUIEntity(),
        teamEntity = this.teamDetails.toUIEntity()
    )
}