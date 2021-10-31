package com.ruben.footiescore.usecase

import com.ruben.footiescore.FootballRepository
import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.ErrorEntity
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