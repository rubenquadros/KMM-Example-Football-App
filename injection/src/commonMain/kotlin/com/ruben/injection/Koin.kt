package com.ruben.injection

import org.koin.core.module.Module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
expect fun initKoin(appModule: Module, context: Any? = null)