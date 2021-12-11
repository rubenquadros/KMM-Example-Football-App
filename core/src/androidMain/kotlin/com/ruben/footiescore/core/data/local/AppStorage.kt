package com.ruben.footiescore.core.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Created by Ruben Quadros on 11/12/21
 **/
actual class AppStorage(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("footiescore.core")

    actual suspend fun storeFirstTime(firstTime: Boolean) {
        context.dataStore.edit {
            it[AppStorageKeys.FIRST_TIME_LAUNCH] = firstTime
        }
    }

    actual suspend fun getIsFirstTimeLaunch(): Boolean {
        return context.dataStore.data.map {
            it[AppStorageKeys.FIRST_TIME_LAUNCH] ?: true
        }.first()
    }

    actual suspend fun isUserLoggedIn(): Boolean {
        return context.dataStore.data.map {
            it[AppStorageKeys.IS_USER_LOGGED_IN] ?: false
        }.first()
    }

    actual suspend fun setUserLogin(userLogin: Boolean) {
        context.dataStore.edit {
            it[AppStorageKeys.IS_USER_LOGGED_IN] = userLogin
        }
    }
}