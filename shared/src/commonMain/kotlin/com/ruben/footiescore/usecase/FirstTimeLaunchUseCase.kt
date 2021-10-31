package com.ruben.footiescore.usecase

import com.ruben.footiescore.FootballRepository

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