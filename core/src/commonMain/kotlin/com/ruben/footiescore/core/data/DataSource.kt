package com.ruben.footiescore.core.data

import com.ruben.cache.db.FootieScoreDB
import com.ruben.footiescore.core.data.local.AppStorage
import com.ruben.footiescore.core.data.remote.ApiSource


/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface DataSource {
    fun api(): ApiSource
    fun appStorage(): AppStorage
    fun database(): FootieScoreDB
}