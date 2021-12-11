package com.ruben.injection.core

import com.ruben.footiescore.core.data.di.koinCoreModule

/**
 * Created by Ruben Quadros on 11/12/21
 **/
val coreKoinModules = listOf(
    coreDataModule, coreRepositoryModule, coreRemoteModule, coreUseCaseModule, koinCoreModule
)