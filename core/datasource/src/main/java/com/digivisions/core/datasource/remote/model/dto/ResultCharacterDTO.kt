package com.digivisions.core.datasource.remote.model.dto


import kotlinx.serialization.SerialName


data class ResultCharacterDTO(
    @SerialName("comics")
    val comics: ComicsDTO,
    @SerialName("description")
    val description: String,
    @SerialName("events")
    val events: EventsDTO,
    @SerialName("id")
    val id: Int,
    @SerialName("modified")
    val modified: String,
    @SerialName("name")
    val name: String,
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("series")
    val series: SeriesDTO,
    @SerialName("stories")
    val stories: StoriesDTO,
    @SerialName("thumbnail")
    val thumbnail: ThumbnailDTO,

)