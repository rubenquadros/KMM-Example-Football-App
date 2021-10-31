package com.ruben.remote

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.exceptions.RemoteException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

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
            val response: RESPONSE = client.get(baseUrl + endPoint) {
                params.forEach {
                    parameter(it.key, it.value)
                }
                parameter("plan", "TIER_ONE")
            }
            ApiResponse.Success(response)
        } catch (e: RemoteException) {
            mapError(e)
        }
    }

    suspend inline fun <reified RESPONSE, ERROR> post(
        endPoint: String,
        requestBody: Any?
    ): ApiResponse<RESPONSE, ERROR> {
        return try {
            val response: RESPONSE = client.post(baseUrl + endPoint) {
                requestBody?.let {
                    contentType(ContentType.Application.Json)
                    body = it
                }
            }
            ApiResponse.Success(response)
        } catch (e: RemoteException) {
            return mapError(e)
        }
    }

    fun <RESPONSE, ERROR> mapError(exception: RemoteException): ApiResponse<RESPONSE, ERROR> {
        return when (exception) {
            is RemoteException.ServerError -> ApiResponse.ErrorNoBody(exception.code)
            is RemoteException.ClientError -> ApiResponse.ErrorNoBody(exception.code)
            is RemoteException.RedirectError -> ApiResponse.ErrorNoBody(exception.code)
            else -> ApiResponse.UnknownError
        }
    }
}