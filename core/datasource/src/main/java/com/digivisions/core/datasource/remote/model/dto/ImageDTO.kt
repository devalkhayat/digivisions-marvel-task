package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class ImageDTO(
    @SerialName("extension")
    val extension: String,
    @SerialName("path")
    val path: String
)