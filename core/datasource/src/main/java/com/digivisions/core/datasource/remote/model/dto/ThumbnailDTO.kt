package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ThumbnailDTO(
    @SerialName("extension")
    val extension: String,
    @SerialName("path")
    val path: String
)