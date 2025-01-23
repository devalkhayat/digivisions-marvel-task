package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class CharactersDTO(
    @SerialName("available")
    val available: Int,
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("items")
    val items: List<ItemDTO>,
    @SerialName("returned")
    val returned: Int
)