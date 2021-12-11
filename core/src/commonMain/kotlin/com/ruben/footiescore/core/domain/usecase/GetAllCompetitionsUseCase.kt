package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.AllCompetitionEntity
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.entity.ErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class GetAllCompetitionsUseCase(private val repository: FootballRepository) :
    BaseUseCase<Unit, List<AllCompetitionEntity>, ErrorEntity>() {

    override suspend fun execute(request: Unit): Flow<BaseEntity<List<AllCompetitionEntity>, ErrorEntity>> = flow {
        emit(BaseEntity.Loading)
        val result = repository.getAllCompetitions()
        emit(result)
    }
}