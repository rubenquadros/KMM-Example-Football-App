package com.ruben.remote.rest

import com.ruben.remote.model.ApiResponse
import com.ruben.remote.model.GetAllCompetitionsResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
interface RestApi {
    suspend fun getAllCompetitions(): ApiResponse<GetAllCompetitionsResponse, Nothing>
}