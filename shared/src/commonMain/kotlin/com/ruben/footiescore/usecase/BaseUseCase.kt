package com.ruben.footiescore.usecase

import com.ruben.footiescore.dispatcher.DispatcherProvider
import com.ruben.footiescore.entity.BaseEntity
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
abstract class BaseUseCase<REQUEST, RESPONSE, ERROR> : KoinComponent {

    private val dispatcherProvider: DispatcherProvider by inject()

    suspend operator fun invoke(request: REQUEST): BaseEntity<RESPONSE, ERROR> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            execute(request)
        }
    }

    abstract suspend fun execute(request: REQUEST): BaseEntity<RESPONSE, ERROR>

}

abstract class BaseStorageUseCase<REQUEST, RESPONSE> : KoinComponent {

    private val dispatcherProvider: DispatcherProvider by inject()

    suspend operator fun invoke(request: REQUEST): RESPONSE {
        return withContext(dispatcherProvider.dispatcherDefault) {
            execute(request)
        }
    }

    abstract suspend fun execute(request: REQUEST): RESPONSE
}

abstract class BaseNoResponseUseCase<REQUEST> : KoinComponent {

    private val dispatcherProvider: DispatcherProvider by inject()

    suspend operator fun invoke(request: REQUEST) {
        withContext(dispatcherProvider.dispatcherDefault) {
            execute(request)
        }
    }

    abstract suspend fun execute(request: REQUEST)
}