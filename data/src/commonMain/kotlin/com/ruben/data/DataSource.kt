package com.ruben.data

import com.ruben.cache.AppStorage
import com.ruben.cache.db.FootieScoreDB
import com.ruben.remote.ApiSource
import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface DataSource {
    fun api(): ApiSource
    fun appStorage(): AppStorage
    fun database(): FootieScoreDB
}