package com.digivisions.core.navigation

import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
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
    data class PreviewScreenRoute(val dataList:ArrayList<ComicModel>):HomeFeatureRoutes()
    @Serializable
    data object FindScreenRoute:HomeFeatureRoutes()
}
