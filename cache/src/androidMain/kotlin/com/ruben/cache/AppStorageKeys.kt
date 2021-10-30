package com.ruben.cache

import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created by Ruben Quadros on 30/10/21
 **/
object AppStorageKeys {
    val FIRST_TIME_LAUNCH = booleanPreferencesKey("first_time_launch")
}