package com.aerc.architectcoders.architectimdb.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<RemoteMovie>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)