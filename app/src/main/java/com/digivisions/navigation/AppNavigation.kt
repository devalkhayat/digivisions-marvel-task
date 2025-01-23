package com.digivisions.marvelcharacters.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.digivisions.core.navigation.HomeFeatureRoutes


@Composable
fun AppNavGraph(navController: NavHostController,navigationProvider: NavigationProvider){

    NavHost(navController = navController, startDestination = HomeFeatureRoutes.Graph ){
        navigationProvider.home.registerGraph(navController,this)

    }

}