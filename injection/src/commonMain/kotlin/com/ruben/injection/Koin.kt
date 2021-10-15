package com.ruben.injection

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by Ruben Quadros on 15/10/21
 **/
fun initKoin(appDeclaration: KoinAppDeclaration = {}, appModule: Module) = startKoin {
    appDeclaration()
    modules(listOf(appModule, sharedModule, useCaseModule, dataModule, repositoryModule, remoteModule))
}