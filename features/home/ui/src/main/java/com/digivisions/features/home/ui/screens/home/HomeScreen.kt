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
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.AppTopBar
import com.digivisions.core.common.components.CircleLoading
import com.digivisions.core.common.components.LoadNetworkImage

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
            CircleLoading()
        }
    }
    if(result.error.isNotBlank()){
        ScreenPlaceHolder{
            Text(text = result.error)
        }
    }
    result.data?.let {

        Box(modifier = Modifier.fillMaxSize()){

            Display(homeScreenViewModel, it.collectAsLazyPagingItems())
        }
    }

}

@Composable
fun Display(
    homeScreenViewModel: HomeScreenViewModel,
    lazyPagingItems: LazyPagingItems<CharacterModel>
){
    Scaffold(modifier = Modifier.fillMaxSize(),
             topBar ={ AppTopBar{
                 homeScreenViewModel._currentNavController.value?.navigate(
                         HomeFeatureRoutes.FindScreenRoute
                         )

             } }
    ) { innerPadding->


        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {



            items(count = lazyPagingItems.itemCount){
                DrawItem(homeScreenViewModel,lazyPagingItems.get(it)!!)
            }

            when(lazyPagingItems.loadState.append){
                is LoadState.Error ->  item { ErrorItem("Error occurred") }
                LoadState.Loading -> item { LoadingItem() }
                is LoadState.NotLoading -> Unit
            }

            when(lazyPagingItems.loadState.refresh){
                is LoadState.Error -> item { ErrorItem("Error occurred") }
                LoadState.Loading -> item { LoadingItem() }
                is LoadState.NotLoading -> Unit
            }






        }
    }

}

@Composable
fun LoadingItem(){
    ScreenPlaceHolder{
        Box(modifier = Modifier.fillMaxWidth().height(50.dp), contentAlignment = Alignment.Center){
            CircleLoading()
        }

    }
}

@Composable
fun ErrorItem(message:String){
    ScreenPlaceHolder {
        Box(modifier = Modifier.fillMaxWidth().height(50.dp), contentAlignment = Alignment.Center){
            AppLabel(caption = message, style = MaterialTheme.typography.labelMedium, color = AppColors.Text2)
        }

    }
}
@Composable
fun DrawItem(homeScreenViewModel: HomeScreenViewModel,data: CharacterModel) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(225.dp)
        .clickable {

            homeScreenViewModel._currentNavController.value?.navigate(
                HomeFeatureRoutes.DetailsScreenRoute(
                    data
                )
            )


        }) {

        LoadNetworkImage(url = data.avatar, scale = ContentScale.FillBounds)

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)) {

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(color = getColor(AppColors.ContainerBackground1))
                    .align(alignment = Alignment.BottomStart)
                    ,
                contentAlignment = Alignment.Center
            )
            {
                AppLabel(
                    caption = data.name,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    maxline = 1,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 35.dp, top = 16.dp)
                )



            }

        }
    }

}











