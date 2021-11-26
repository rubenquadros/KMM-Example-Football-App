package com.ruben.remote

import com.ruben.remote.model.ApiResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

/**
 * Created by Ruben Quadros on 15/10/21
 **/
val client = HttpClient(CIO) {

    expectSuccess = false

    install(Logging) {
        level = LogLevel.ALL
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(HttpTimeout) {
        requestTimeoutMillis = ApiConstants.TIME_OUT
        connectTimeoutMillis = ApiConstants.TIME_OUT
        socketTimeoutMillis = ApiConstants.TIME_OUT
    }

    defaultRequest {
        header(ApiConstants.AUTH_HEADER, BuildKonfig.API_KEY)
    }
}

fun HttpClient.addResponseInterceptor() {

    fun isContentEmpty(response: HttpResponse) = response.contentLength() == null || response.contentLength() == 0L

    this.responsePipeline.intercept(HttpResponsePipeline.Transform) { (info, body) ->
        val response = if (context.response.status.isSuccess()) {
            if (isContentEmpty(context.response)) ApiResponse.SuccessNoBody
            else body
        } else {
            if (isContentEmpty(context.response)) ApiResponse.ErrorNoBody(context.response.status.value)
            else body
        }

        proceedWith(HttpResponseContainer(info, response))
    }
}