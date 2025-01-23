package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class EventsDTO(
    @SerialName("available")
    val available: Int,
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("items")
    val items: List<ItemDTO>,
    @SerialName("returned")
    val returned: Int
)