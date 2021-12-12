package com.ruben.footiescore.shared.remote.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject

/**
 * Created by Ruben Quadros on 15/10/21
 **/
@OptIn(InternalSerializationApi::class)
@Serializable(with = ApiResponseSerializer::class)
sealed class ApiResponse<out SUCCESS, out ERROR> {

    data class Success<SUCCESS>(val body: SUCCESS): ApiResponse<SUCCESS, Nothing>()

    object SuccessNoBody: ApiResponse<Nothing, Nothing>()

    data class Error<ERROR>(val error: ERROR): ApiResponse<Nothing, ERROR>()

    data class ErrorNoBody(val code: Int): ApiResponse<Nothing, Nothing>()

    object DatabaseError: ApiResponse<Nothing, Nothing>()

    object UnknownError: ApiResponse<Nothing, Nothing>()

}

@InternalSerializationApi
class ApiResponseSerializer<S, E>(
    private val successSerializer: KSerializer<S>,
    private val errorSerializer: KSerializer<E>
) : KSerializer<ApiResponse<S, E>> {
    override fun deserialize(decoder: Decoder): ApiResponse<S, E> {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()

        return when {
            (element is JsonObject && "errorCode" in element) -> ApiResponse.Error(
                decoder.json.decodeFromJsonElement(
                    errorSerializer,
                    element
                )
            )
            (element is JsonObject || element is JsonArray) -> ApiResponse.Success(
                decoder.json.decodeFromJsonElement(
                    successSerializer,
                    element
                )
            )
            else -> ApiResponse.UnknownError
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("ApiResponse", PolymorphicKind.SEALED) {
        element("ErrorResponse", errorSerializer.descriptor)
        element("SuccessResponse", successSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: ApiResponse<S, E>) {
        require(encoder is JsonEncoder)
        when (value) {
            is ApiResponse.Success -> encoder.json.encodeToJsonElement(successSerializer, value.body)
            is ApiResponse.Error -> encoder.json.encodeToJsonElement(errorSerializer, value.error)
            is ApiResponse.ErrorNoBody, ApiResponse.SuccessNoBody, ApiResponse.DatabaseError, ApiResponse.UnknownError -> {
                //do nothing
            }
        }
    }

}