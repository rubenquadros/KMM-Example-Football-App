package com.ruben.footiescore.shared.domain.entity

import com.ruben.footiescore.shared.remote.model.ApiResponse

/**
 * Created by Ruben Quadros on 15/10/21
 **/
sealed class BaseEntity<out RESPONSE, out ERROR> {
    object Loading: BaseEntity<Nothing, Nothing>()

    data class Success<RESPONSE>(val body: RESPONSE): BaseEntity<RESPONSE, Nothing>()

    data class Error<ERROR>(val error: ERROR): BaseEntity<Nothing, ERROR>()

    object SuccessNoBody: BaseEntity<Nothing, Nothing>()

    object ClientError: BaseEntity<Nothing, Nothing>()

    object ServerError: BaseEntity<Nothing, Nothing>()

    object ForbiddenAction: BaseEntity<Nothing, Nothing>()

    object UnknownError: BaseEntity<Nothing, Nothing>()

}

internal fun ApiResponse<Nothing, Nothing>.toUIEntity(): BaseEntity<Nothing, Nothing> {
    return when (this) {
        is ApiResponse.SuccessNoBody -> BaseEntity.SuccessNoBody
        is ApiResponse.ErrorNoBody ->  mapErrorEntity(this.code)
        else -> BaseEntity.UnknownError
    }
}

fun <T>mapErrorEntity(code: Int): BaseEntity<T, Nothing> {
    return when (code) {
        in (400..499) -> {
            if (code == 403) {
                BaseEntity.ForbiddenAction
            } else {
                BaseEntity.ClientError
            }
        }
        in (500..599) -> BaseEntity.ServerError
        else -> BaseEntity.UnknownError
    }
}