package com.ruben.data

import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface DataSource {
    fun api(): RestApi
}