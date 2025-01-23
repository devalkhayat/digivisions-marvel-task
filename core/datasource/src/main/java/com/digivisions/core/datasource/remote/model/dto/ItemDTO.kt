package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ItemDTO(
    @SerialName("name")
    val name: String,
    @SerialName("resourceURI")
    val resourceURI: String
)