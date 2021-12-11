package com.ruben.footiescore.core.data.remote

import com.ruben.footiescore.core.data.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 27/11/21
 **/
interface ApiSource {
    fun restApi(): RestApi
}