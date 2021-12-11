package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class SearchTeamUseCase(private val repository: FootballRepository) :
    BaseUseCase<SearchTeamUseCase.RequestValue, List<SearchTeamEntity>, Nothing>() {

    override suspend fun execute(request: RequestValue): Flow<BaseEntity<List<SearchTeamEntity>, Nothing>> = flow {
        emit(BaseEntity.Loading)
        val result = repository.searchTeam(request.searchQuery)
        emit(result)
    }

    data class RequestValue(val searchQuery: String)
}