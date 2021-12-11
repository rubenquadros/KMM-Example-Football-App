package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.shared.domain.usecase.BaseNoResponseUseCase
import com.ruben.footiescore.shared.domain.usecase.BaseStorageUseCase

/**
 * Created by Ruben Quadros on 30/10/21
 **/
class GetFirstTimeLaunchUseCase(private val repository: FootballRepository) :
    BaseStorageUseCase<Unit, Boolean>() {

    override suspend fun execute(request: Unit): Boolean {
        return repository.getIsFirstTimeLaunch()
    }
}

class StoreFirstTimeLaunchUseCase(private val repository: FootballRepository) :
    BaseNoResponseUseCase<StoreFirstTimeLaunchUseCase.RequestValue>() {

    override suspend fun execute(request: RequestValue) {
        repository.setFirstTimeLaunch(request.firstTimeLaunch)
    }

    data class RequestValue(val firstTimeLaunch: Boolean)
}