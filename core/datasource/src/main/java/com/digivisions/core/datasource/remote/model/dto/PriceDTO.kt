package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class PriceDTO(
    @SerialName("price")
    val price: Double,
    @SerialName("type")
    val type: String
)