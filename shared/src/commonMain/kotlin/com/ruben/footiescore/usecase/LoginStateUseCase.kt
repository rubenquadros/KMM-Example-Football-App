package com.ruben.footiescore.usecase

import com.ruben.footiescore.FootballRepository

/**
 * Created by Ruben Quadros on 28/11/21
 **/
class GetLoginStateUseCase(private val repository: FootballRepository) :
    BaseStorageUseCase<Unit, Boolean>() {

    override suspend fun execute(request: Unit): Boolean {
        return repository.getIsUserLoggedIn()
    }
}

class SetLoginStateUseCase(private val repository: FootballRepository) :
    BaseNoResponseUseCase<SetLoginStateUseCase.RequestValue>() {

    override suspend fun execute(request: RequestValue) {
        repository.setIsUserLoggedIn(request.isUseLoggedIn)
    }

    data class RequestValue(val isUseLoggedIn: Boolean)
}