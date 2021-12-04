package com.ruben.remote.ktor

import com.ruben.remote.BuildKonfig
import com.ruben.remote.model.ApiResponse
import com.ruben.remote.exceptions.RemoteException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.*

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class ApiClient {

    val baseUrl = BuildKonfig.BASE_URL

    suspend inline fun <reified RESPONSE, ERROR> get(
        endPoint: String,
        params: Map<String, Any> = mapOf()
    ): ApiResponse<RESPONSE, ERROR> {
        return try {
            client.addResponseInterceptor()
            val response: ApiResponse<RESPONSE, ERROR> = client.get(baseUrl + endPoint) {
                params.forEach {
                    parameter(it.key, it.value)
                }
            }
            response
        } catch (e: RemoteException) {
            ApiResponse.UnknownError
        }
    }

    suspend inline fun <reified RESPONSE, ERROR> post(
        endPoint: String,
        requestBody: Any
    ): ApiResponse<RESPONSE, ERROR> {
        return try {
            client.addResponseInterceptor()
            val response: ApiResponse<RESPONSE, ERROR> = client.post(baseUrl + endPoint) {
                contentType(ContentType.Application.Json)
                body = requestBody
            }
            response
        } catch (e: RemoteException) {
            return ApiResponse.UnknownError
        }
    }
}

