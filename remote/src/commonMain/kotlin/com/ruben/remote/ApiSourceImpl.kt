package com.ruben.remote

import com.ruben.remote.algolia.AlgoliaApi
import com.ruben.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class ApiSourceImpl(private val restApi: RestApi, private val algoliaApi: AlgoliaApi): ApiSource {
    override fun restApi(): RestApi = restApi

    override fun algoliaApi(): AlgoliaApi = algoliaApi
}