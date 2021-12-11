package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.SearchTeamResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.entity.mapErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class SearchTeamUseCase(private val repository: FootballRepository) :
    BaseUseCase<SearchTeamUseCase.RequestValue, List<SearchTeamEntity>, Nothing>() {

    override suspend fun execute(request: RequestValue): Flow<BaseEntity<List<SearchTeamEntity>, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.searchTeam(request.searchQuery)) {
            is ApiResponse.Success -> {
                emit(BaseEntity.Success(result.body.toUIEntity()))
            }
            is ApiResponse.ErrorNoBody -> {
                emit(mapErrorEntity(result.code))
            }
            else -> emit(BaseEntity.UnknownError)
        }
    }

    data class RequestValue(val searchQuery: String)
}

internal fun SearchTeamResponse.toUIEntity(): List<SearchTeamEntity> {
    fun SearchTeamResponse.Hit.toEntity(): SearchTeamEntity =
        SearchTeamEntity(
            id = this.id,
            name = this.name,
            area = this.area,
            image = this.crestUrl.orEmpty()
        )

    return this.hits.map { it.toEntity() }
}