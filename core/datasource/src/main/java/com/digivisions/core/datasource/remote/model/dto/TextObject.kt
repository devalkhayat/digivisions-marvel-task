package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class TextObject(
    @SerialName("language")
    val language: String,
    @SerialName("text")
    val text: String,
    @SerialName("type")
    val type: String
)