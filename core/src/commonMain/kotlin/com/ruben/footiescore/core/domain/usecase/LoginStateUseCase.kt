package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.shared.domain.usecase.BaseNoResponseUseCase
import com.ruben.footiescore.shared.domain.usecase.BaseStorageUseCase

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
        repository.setIsUserLoggedIn(request.isUserLoggedIn)
    }

    data class RequestValue(val isUserLoggedIn: Boolean)
}