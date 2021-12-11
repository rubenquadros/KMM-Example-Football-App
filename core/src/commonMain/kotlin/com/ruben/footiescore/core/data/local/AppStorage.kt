package com.ruben.footiescore.core.data.local

/**
 * Created by Ruben Quadros on 11/12/21
 **/
expect class AppStorage {
    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun storeFirstTime(firstTime: Boolean)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun setUserLogin(userLogin: Boolean)
}