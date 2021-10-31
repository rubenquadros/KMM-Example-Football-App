package com.ruben.cache

import com.ruben.cache.db.FootieScoreDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

/**
 * Created by Ruben Quadros on 31/10/21
 **/
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(FootieScoreDB.Schema, "footiescore.db")
    }
}