package com.ruben.footiescore.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class DispatcherProviderImpl: DispatcherProvider {

    override fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    override fun dispatcherMain(): CoroutineDispatcher = Dispatchers.Main
}