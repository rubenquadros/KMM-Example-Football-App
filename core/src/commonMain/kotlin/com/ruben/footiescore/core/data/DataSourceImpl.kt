package com.ruben.footiescore.core.data

import com.ruben.cache.DatabaseDriverFactory
import com.ruben.cache.db.FootieScoreDB
import com.ruben.footiescore.core.data.local.AppStorage
import com.ruben.footiescore.core.data.remote.ApiSource

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class DataSourceImpl(
    private val apiSource: ApiSource,
    private val appStorage: AppStorage,
    private val databaseDriverFactory: DatabaseDriverFactory
) : DataSource {
    override fun api(): ApiSource = apiSource

    override fun appStorage(): AppStorage = appStorage

    override fun database(): FootieScoreDB = FootieScoreDB(databaseDriverFactory.createDriver())
}