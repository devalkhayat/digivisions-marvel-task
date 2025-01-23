package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class DataDTO<T>(
    @SerialName("count")
    val count: Int,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("results")
    val results: List<T>,
    @SerialName("total")
    val total: Int
)