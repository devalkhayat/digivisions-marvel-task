package com.digivisions.core.navigation

import com.digivisions.features.home.domain.model.character.CharacterModel
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
    data class PreviewScreenRoute(val url:String,val name:String):HomeFeatureRoutes()
}
