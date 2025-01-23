package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class ResultComicDetailsDTO(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("thumbnail")
        val thumbnail: ThumbnailDTO,
)