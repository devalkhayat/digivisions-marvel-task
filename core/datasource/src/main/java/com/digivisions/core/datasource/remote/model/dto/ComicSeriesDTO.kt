package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class ComicSeriesDTO(
    @SerialName("name")
    val name: String,
    @SerialName("resourceURI")
    val resourceURI: String
)