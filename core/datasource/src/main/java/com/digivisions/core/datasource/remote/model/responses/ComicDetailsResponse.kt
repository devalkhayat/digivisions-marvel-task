package com.digivisions.core.datasource.remote.model.responses


import com.digivisions.core.datasource.remote.model.dto.ResultComicDetailsDTO
import com.digivisions.core.datasource.remote.model.dto.DataDTO
import kotlinx.serialization.SerialName


data class ComicDetailsResponse(
    @SerialName("attributionHTML")
    val attributionHTML: String,
    @SerialName("attributionText")
    val attributionText: String,
    @SerialName("code")
    val code: Int,
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val data: DataDTO<ResultComicDetailsDTO>,
    @SerialName("etag")
    val etag: String,
    @SerialName("status")
    val status: String
)