package com.digivisions.features.home.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.digivisions.core.navigation.CharacterModelType
import com.digivisions.core.navigation.ComicModelType
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.core.navigation.NavigationApi
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.ui.screens.details.DetailsScreen
import com.digivisions.features.home.ui.screens.details.DetailsScreenViewModel
import com.digivisions.features.home.ui.screens.details.PreviewScreen
import com.digivisions.features.home.ui.screens.find.FindScreen
import com.digivisions.features.home.ui.screens.home.HomeScreen
import kotlin.reflect.typeOf


internal object InternalHomeFeatureApi: NavigationApi {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {

        navGraphBuilder.navigation<HomeFeatureRoutes.Graph>(startDestination = HomeFeatureRoutes.HomeScreenRoute) {

            composable<HomeFeatureRoutes.HomeScreenRoute> {
                HomeScreen(navController)
            }
            dialog<HomeFeatureRoutes.DetailsScreenRoute>(typeMap = mapOf(
                typeOf<CharacterModel>() to CharacterModelType,
            ), dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false
            )
            ) { entry ->

                val data = entry.toRoute<HomeFeatureRoutes.DetailsScreenRoute>()
                DetailsScreen(navHostController = navController, currentItem = data.character)

            }


            dialog<HomeFeatureRoutes.PreviewScreenRoute>(typeMap = mapOf(
                typeOf<List<ComicModel>>() to ComicModelType,
            )) { entry ->

                val data = entry.toRoute<HomeFeatureRoutes.PreviewScreenRoute>()

                PreviewScreen(navHostController = navController, comicsList = data.dataList)
            }



            dialog<HomeFeatureRoutes.FindScreenRoute>(typeMap = mapOf(
                typeOf<CharacterModel>() to CharacterModelType,
            ), dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false
            )
            ) { entry ->

                val data = entry.toRoute<HomeFeatureRoutes.FindScreenRoute>()
                FindScreen(navHostController = navController)

            }

        }
    }

}



/* composable<HomeFeatureRoutes.DetailsScreenRoute>(
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
}*/