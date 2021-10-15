package com.ruben.remote.model

/**
 * Created by Ruben Quadros on 15/10/21
 **/
sealed class ApiResponse<out SUCCESS, out ERROR> {

    data class Success<SUCCESS>(val body: SUCCESS): ApiResponse<SUCCESS, Nothing>()

    data class Error<ERROR>(val error: ERROR, val code: Int): ApiResponse<Nothing, ERROR>()

    data class ErrorNoBody(val code: Int): ApiResponse<Nothing, Nothing>()

    object UnknownError: ApiResponse<Nothing, Nothing>()

}