package com.ruben.remote

import com.ruben.remote.algolia.AlgoliaApi
import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 27/11/21
 **/
interface ApiSource {
    fun restApi(): RestApi
    fun algoliaApi(): AlgoliaApi
}