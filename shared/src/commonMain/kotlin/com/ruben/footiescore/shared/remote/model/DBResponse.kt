package com.ruben.footiescore.shared.remote.model

/**
 * Created by Ruben Quadros on 19/12/21
 **/
sealed class DBResponse<out DATA> {
    data class Success<DATA>(val data: DATA): DBResponse<DATA>()

    object Error: DBResponse<Nothing>()
}
