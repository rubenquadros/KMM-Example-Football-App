package com.ruben.footiescore.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class DispatcherProviderImpl: DispatcherProvider {
    override val dispatcherDefault: CoroutineDispatcher
        get() = Dispatchers.Default

    override val dispatcherMain: CoroutineDispatcher
        get() = Dispatchers.Main
}