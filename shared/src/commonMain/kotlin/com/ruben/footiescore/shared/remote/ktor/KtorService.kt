package com.ruben.footiescore.shared.remote.ktor

import com.ruben.footiescore.core.BuildKonfig
import com.ruben.footiescore.shared.remote.ApiConstants
import com.ruben.footiescore.shared.remote.model.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentLength
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class KtorService {

    val client = HttpClient {

        expectSuccess = false

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
            url {
                host = BuildKonfig.BASE_URL
                protocol = URLProtocol.HTTPS
            }
            header(ApiConstants.AUTH_HEADER, BuildKonfig.API_KEY)
            contentType(ContentType.Application.Json)
        }
    }.also {
        it.addResponseInterceptor()
    }
}

fun HttpClient.addResponseInterceptor() {

    fun isBodyEmpty(body: Any): Boolean {
        val jsonResponse = (body as? ApiResponse.Success<*>)?.body
        return jsonResponse == null
    }

    this.responsePipeline.intercept(HttpResponsePipeline.Transform) { (info, body) ->
        val response = if (context.response.status.isSuccess()) {
            if (isBodyEmpty(body)) ApiResponse.SuccessNoBody
            else body
        } else {
            if (isBodyEmpty(body)) ApiResponse.ErrorNoBody(context.response.status.value)
            else body
        }

        proceedWith(HttpResponseContainer(info, response))
    }
}

