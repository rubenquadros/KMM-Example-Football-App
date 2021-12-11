package com.ruben.footiescore.core.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created by Ruben Quadros on 30/10/21
 **/
object AppStorageKeys {
    val FIRST_TIME_LAUNCH = booleanPreferencesKey("first_time_launch")
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
}