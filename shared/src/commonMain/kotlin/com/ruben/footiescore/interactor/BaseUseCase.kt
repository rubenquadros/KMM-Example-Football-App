package com.ruben.footiescore.interactor

import com.ruben.footiescore.entity.BaseEntity

/**
 * Created by Ruben Quadros on 15/10/21
 **/
abstract class BaseUseCase<REQUEST, RESPONSE, ERROR> {

    suspend operator fun invoke(request: REQUEST): BaseEntity<RESPONSE, ERROR> {
        return execute(request)
    }

    abstract suspend fun execute(request: REQUEST): BaseEntity<RESPONSE, ERROR>

}