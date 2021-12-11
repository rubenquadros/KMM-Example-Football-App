package com.ruben.footiescore.core.data.remote.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SearchTeamResponse(
    @SerialName("exhaustiveNbHits")
    val exhaustiveNbHits: Boolean,
    @SerialName("exhaustiveTypo")
    val exhaustiveTypo: Boolean,
    @SerialName("hits")
    val hits: List<Hit>,
    @SerialName("hitsPerPage")
    val hitsPerPage: Int,
    @SerialName("nbHits")
    val nbHits: Int,
    @SerialName("nbPages")
    val nbPages: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("params")
    val params: String,
    @SerialName("processingTimeMS")
    val processingTimeMS: Int,
    @SerialName("query")
    val query: String
) {
    @Serializable
    data class Hit(
        @SerialName("area")
        val area: String,
        @SerialName("crestUrl")
        val crestUrl: String? = null,
        @SerialName("_highlightResult")
        val highlightResult: HighlightResult,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("objectID")
        val objectID: String
    ) {
        @Serializable
        data class HighlightResult(
            @SerialName("name")
            val name: Name
        ) {
            @Serializable
            data class Name(
                @SerialName("fullyHighlighted")
                val fullyHighlighted: Boolean? = null,
                @SerialName("matchLevel")
                val matchLevel: String,
                @SerialName("matchedWords")
                val matchedWords: List<String>,
                @SerialName("value")
                val value: String
            )
        }
    }
}