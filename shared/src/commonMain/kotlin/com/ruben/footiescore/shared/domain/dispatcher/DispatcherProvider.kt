package com.ruben.footiescore.shared.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface DispatcherProvider {

    val dispatcherDefault: CoroutineDispatcher

    val dispatcherMain: CoroutineDispatcher
}