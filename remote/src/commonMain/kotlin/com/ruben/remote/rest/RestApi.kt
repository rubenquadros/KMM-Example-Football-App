package com.ruben.remote.rest

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.request.LoginRequest
import com.ruben.remote.model.response.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface RestApi {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<Nothing, Nothing>
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing>
}