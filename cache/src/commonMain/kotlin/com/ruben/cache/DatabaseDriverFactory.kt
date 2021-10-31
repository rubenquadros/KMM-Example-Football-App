package com.ruben.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 * Created by Ruben Quadros on 31/10/21
 **/
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}