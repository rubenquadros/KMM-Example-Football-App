package com.ruben.cache

import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 30/10/21
 **/
actual val koinCacheModule = module {
    single { AppStorage() }
    single { DatabaseDriverFactory() }
}