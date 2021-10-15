package com.ruben.data

import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class DataSourceImpl(private val restApi: RestApi): DataSource {
    override fun api(): RestApi = restApi
}