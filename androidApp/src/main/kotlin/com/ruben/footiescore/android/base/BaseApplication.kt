package com.ruben.footiescore.android.base

import android.app.Application
import com.ruben.footiescore.android.di.viewModelModule
import com.ruben.injection.initKoin

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(appModule = viewModelModule)
    }
}