package com.digivisions.features.home.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.digivisions.core.navigation.CharacterModelType
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.core.navigation.NavigationApi
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.ui.screens.details.DetailsScreen
import com.digivisions.features.home.ui.screens.details.PreviewScreen
import com.digivisions.features.home.ui.screens.home.HomeScreen
import kotlin.reflect.typeOf


internal object InternalHomeFeatureApi: NavigationApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {

        navGraphBuilder.navigation<HomeFeatureRoutes.Graph>(startDestination = HomeFeatureRoutes.HomeScreenRoute) {
            composable<HomeFeatureRoutes.HomeScreenRoute> {
                HomeScreen(navController)
            }
            composable<HomeFeatureRoutes.DetailsScreenRoute>(
                typeMap = mapOf(
                    typeOf<CharacterModel>() to CharacterModelType,
                ), enterTransition = {
                    return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(900))
                }, exitTransition = {
                    return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400))
                }
            ) { entry ->

                val data = entry.toRoute<HomeFeatureRoutes.DetailsScreenRoute>()

                DetailsScreen(navHostController = navController, currentItem = data.character)
            }

            dialog<HomeFeatureRoutes.PreviewScreenRoute>() { entry ->

                val data = entry.toRoute<HomeFeatureRoutes.PreviewScreenRoute>()

                PreviewScreen(navHostController = navController, url=data.url,name=data.name)
            }

        }
    }

}