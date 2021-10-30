package com.ruben.cache

/**
 * Created by Ruben Quadros on 30/10/21
 **/
actual class AppStorage() {
    actual suspend fun storeFirstTime(firstTime: Boolean) {
    }

    actual suspend fun getIsFirstTimeLaunch(): Boolean {
        return true
    }
}