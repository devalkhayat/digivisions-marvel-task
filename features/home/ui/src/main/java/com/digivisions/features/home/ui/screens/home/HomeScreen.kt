package com.digivisions.features.home.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.AppTopBar
import com.digivisions.core.common.components.LoadNetworkImage

import com.digivisions.core.common.rememberWindowInfo
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor


import com.digivisions.core.common.components.ScreenPlaceHolder
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.features.home.domain.model.character.CharacterModel


@Composable
fun HomeScreen(navHostController: NavHostController,homeScreenViewModel:HomeScreenViewModel= hiltViewModel()){

    LaunchedEffect(Unit) {

       homeScreenViewModel.getAll()
        homeScreenViewModel._currentNavController.value=navHostController
    }

    val result=homeScreenViewModel.resultInfo.value

    if(result.isLoading){
        ScreenPlaceHolder{
            CircularProgressIndicator(color = getColor(AppColors.CircleProgress))
        }
    }
    if(result.error.isNotBlank()){
        ScreenPlaceHolder{
            Text(text = result.error)
        }
    }
    result.data?.let {

        Box(modifier = Modifier.fillMaxSize()){
            Display(homeScreenViewModel,it.characters)
        }
    }

}

@Composable
fun Display(homeScreenViewModel: HomeScreenViewModel,data:ArrayList<CharacterModel>){
    Scaffold(modifier = Modifier.fillMaxSize(),
             topBar ={ AppTopBar() }
    ) { innerPadding->

        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(count = data.size){
                DrawItem(homeScreenViewModel,data[it])
            }


        }
    }

}

@Composable
fun DrawItem(homeScreenViewModel: HomeScreenViewModel,data: CharacterModel) {
    Box(modifier = Modifier.fillMaxWidth().height(200.dp).clickable {

       homeScreenViewModel._currentNavController.value?.navigate(HomeFeatureRoutes.DetailsScreenRoute(data))


    }) {

        LoadNetworkImage(url = data.avatar)

        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 30.dp)) {

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(color = getColor(AppColors.ContainerBackground1))
                    .align(alignment = Alignment.BottomStart)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            )
            {
                AppLabel(
                    caption = data.name,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    maxline = 1,
                )
            }

        }
    }

}











