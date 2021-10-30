package com.ruben.injection

import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Created by Ruben Quadros on 30/10/21
 **/
actual fun initKoin(appModule: Module, context: Any?) {
    startKoin {
        modules(listOf(appModule, sharedModule, useCaseModule, dataModule, repositoryModule, remoteModule))
    }
}