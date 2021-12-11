package com.ruben.injection

import android.content.Context
import com.ruben.injection.core.coreKoinModules
import com.ruben.injection.shared.cacheModule
import com.ruben.injection.shared.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Created by Ruben Quadros on 30/10/21
 **/
actual fun initKoin(appModule: Module, context: Any?) {
    val moduleList: MutableList<Module> = mutableListOf(appModule, cacheModule)

    //add shared modules
    moduleList.add(sharedModule)

    //add core modules
    moduleList.addAll(coreKoinModules)

    startKoin {
        androidContext(context as Context)
        modules(moduleList)
    }
}