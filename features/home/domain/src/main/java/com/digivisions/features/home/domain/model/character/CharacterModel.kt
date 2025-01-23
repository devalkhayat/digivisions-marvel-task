package com.digivisions.features.home.domain.model.character

import kotlinx.serialization.Serializable
import java.util.ArrayList

@Serializable
data class CharacterModel(
        val avatar: String,
        val name: String,
        val description:String,
        var comicList: ArrayList<ComicModel>,
        val seriesList: ArrayList<SeriesModel>,
        val eventList: ArrayList<EventsModel>,
        val storyList: ArrayList<StoriesModel>
)



