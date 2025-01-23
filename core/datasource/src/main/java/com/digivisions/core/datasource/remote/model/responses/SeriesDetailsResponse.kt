package com.digivisions.core.datasource.remote.model.responses


import com.digivisions.core.datasource.remote.model.dto.ResultComicDetailsDTO
import com.digivisions.core.datasource.remote.model.dto.DataDTO
import com.digivisions.core.datasource.remote.model.dto.ResultEventDetailsDTO
import com.digivisions.core.datasource.remote.model.dto.ResultSeriesDetailsDTO
import com.digivisions.core.datasource.remote.model.dto.ResultStoryDetailsDTO
import kotlinx.serialization.SerialName


data class SeriesDetailsResponse(
        @SerialName("attributionHTML")
    val attributionHTML: String,
        @SerialName("attributionText")
    val attributionText: String,
        @SerialName("code")
    val code: Int,
        @SerialName("copyright")
    val copyright: String,
        @SerialName("data")
    val data: DataDTO<ResultSeriesDetailsDTO>,
        @SerialName("etag")
    val etag: String,
        @SerialName("status")
    val status: String
)