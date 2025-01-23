package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class DateDTO(
    @SerialName("date")
    val date: String,
    @SerialName("type")
    val type: String
)