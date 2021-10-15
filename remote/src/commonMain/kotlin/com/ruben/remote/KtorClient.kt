package com.ruben.remote

import com.ruben.remote.exceptions.RemoteException
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
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

    HttpResponseValidator {
        handleResponseException {
            when (it) {
                is ClientRequestException -> throw RemoteException.ClientError(it.response.status.value)
                is ServerResponseException -> throw RemoteException.ServerError(it.response.status.value)
                is RedirectResponseException -> throw RemoteException.RedirectError(it.response.status.value)
                else -> throw RemoteException.UnknownError
            }
        }
    }
}