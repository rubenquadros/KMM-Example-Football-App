package com.ruben.footiescore.interactor

import com.ruben.footiescore.FootballRepository
import com.ruben.footiescore.entity.AllCompetitionEntity
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.entity.ErrorEntity

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class GetAllCompetitionsUseCase(private val repository: FootballRepository) : BaseUseCase<Unit, List<AllCompetitionEntity>, ErrorEntity>() {

    override suspend fun execute(request: Unit): BaseEntity<List<AllCompetitionEntity>, ErrorEntity> {
        return repository.getAllCompetitions()
    }
}