package com.ruben.injection

import android.content.Context
import com.ruben.cache.koinCacheModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Created by Ruben Quadros on 30/10/21
 **/
actual fun initKoin(appModule: Module, context: Any?) {
    startKoin {
        androidContext(context as Context)
        modules(listOf(appModule, sharedModule, useCaseModule, dataModule, repositoryModule, remoteModule, koinCacheModule))
    }
}