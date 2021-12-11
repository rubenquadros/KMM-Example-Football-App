package com.ruben.footiescore.core.data.remote

import com.ruben.footiescore.core.data.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class ApiSourceImpl(private val restApi: RestApi): ApiSource {
    override fun restApi(): RestApi = restApi
}