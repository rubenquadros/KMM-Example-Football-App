package com.ruben.footiescore.shared.domain.usecase

import com.ruben.footiescore.shared.domain.dispatcher.DispatcherProvider
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
abstract class BaseUseCase<REQUEST, RESPONSE, ERROR> : KoinComponent {

    private val dispatcherProvider: DispatcherProvider by inject()

    suspend operator fun invoke(request: REQUEST): Flow<BaseEntity<RESPONSE, ERROR>> {
        return withContext(dispatcherProvider.dispatcherDefault) {
            execute(request)
        }.catch {
            it.printStackTrace()
            emit(BaseEntity.UnknownError)
        }
    }

    abstract suspend fun execute(request: REQUEST): Flow<BaseEntity<RESPONSE, ERROR>>

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