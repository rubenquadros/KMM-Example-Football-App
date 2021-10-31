package com.ruben.data

import com.ruben.cache.AppStorage
import com.ruben.cache.DatabaseDriverFactory
import com.ruben.cache.db.FootieScoreDB
import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class DataSourceImpl(
    private val restApi: RestApi,
    private val appStorage: AppStorage,
    private val databaseDriverFactory: DatabaseDriverFactory
) : DataSource {
    override fun api(): RestApi = restApi

    override fun appStorage(): AppStorage = appStorage

    override fun database(): FootieScoreDB = FootieScoreDB(databaseDriverFactory.createDriver())
}