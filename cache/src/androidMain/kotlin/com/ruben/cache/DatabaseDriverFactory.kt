package com.ruben.cache

import android.content.Context
import com.ruben.cache.db.FootieScoreDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * Created by Ruben Quadros on 31/10/21
 **/
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(FootieScoreDB.Schema, context, "footiescore.db")
    }
}