package com.ruben.footiescore.entity

/**
 * Created by Ruben Quadros on 15/10/21
 **/
sealed class BaseEntity<out RESPONSE, out ERROR> {
    data class Success<RESPONSE>(val body: RESPONSE): BaseEntity<RESPONSE, Nothing>()

    data class Error<ERROR>(val error: ERROR): BaseEntity<Nothing, ERROR>()

    object ErrorNoBody: BaseEntity<Nothing, Nothing>()

    object UnknownError: BaseEntity<Nothing, Nothing>()

}