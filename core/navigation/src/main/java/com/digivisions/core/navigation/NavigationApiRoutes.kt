package com.digivisions.core.navigation

import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import com.digivisions.features.home.domain.model.character.StoriesModel
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeFeatureRoutes{
    @Serializable
    data object Graph:HomeFeatureRoutes()
    @Serializable
    data object HomeScreenRoute:HomeFeatureRoutes()
    @Serializable
    data class DetailsScreenRoute(val character: CharacterModel):HomeFeatureRoutes()
    @Serializable
    data class PreviewScreenRoute(
        val comicList:ArrayList<ComicModel>?=null,
        val seriesList:ArrayList<SeriesModel>?=null,
        val eventList:ArrayList<EventsModel>?=null,
        val storiesList:ArrayList<StoriesModel>?=null):HomeFeatureRoutes()
    @Serializable
    data object FindScreenRoute:HomeFeatureRoutes()
}
