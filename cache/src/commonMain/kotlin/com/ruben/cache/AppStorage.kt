package com.ruben.cache

/**
 * Created by Ruben Quadros on 30/10/21
 **/
expect class AppStorage {

    suspend fun getIsFirstTimeLaunch(): Boolean
    suspend fun storeFirstTime(firstTime: Boolean)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun setUserLogin(userLogin: Boolean)

}