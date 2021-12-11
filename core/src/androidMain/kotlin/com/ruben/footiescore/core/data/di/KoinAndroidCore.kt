package com.ruben.footiescore.core.data.di

import com.ruben.footiescore.core.data.local.AppStorage
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 11/12/21
 **/
actual val koinCoreModule: Module = module {
    single { AppStorage(get()) }
}