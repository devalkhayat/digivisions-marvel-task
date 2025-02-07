package com.digivisions.features.home.ui.screens.find


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.CircleLoading
import com.digivisions.core.common.components.LoadNetworkImage

import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor


import com.digivisions.core.common.components.ScreenPlaceHolder
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.ui.R


@Composable
fun FindScreen(navHostController: NavHostController,findScreenViewModel:FindScreenViewModel= hiltViewModel()){

    LaunchedEffect(Unit) {
        findScreenViewModel._currentNavController.value=navHostController
    }


    val result=findScreenViewModel.resultInfo.value

    Scaffold(modifier = Modifier.fillMaxSize(),
             topBar ={
                 FindSection(findScreenViewModel)
             }
    ) { innerPadding->

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
                Display(modifier = Modifier.padding(innerPadding),findScreenViewModel,it.collectAsLazyPagingItems())
            }
        }

    }

}

@Composable
fun FindSection(findScreenViewModel: FindScreenViewModel){
    var text = remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth().background(getColor(AppColors.ScreenBackground)).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

        val keyboardController = LocalSoftwareKeyboardController.current


        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = { Text("Find...") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.colors(
               focusedContainerColor = Color.White,
               unfocusedContainerColor = Color.White,
               disabledContainerColor = Color.White,

               focusedTextColor = Color.Gray,
               focusedTrailingIconColor = Color.Gray,

               focusedIndicatorColor = Color.Transparent,
               unfocusedIndicatorColor = Color.Transparent,

               focusedPlaceholderColor = Color.Gray,
               unfocusedPlaceholderColor = Color.Gray,

            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    findScreenViewModel.start(text.value.trim())
                    keyboardController?.hide()
                }
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(com.digivisions.core.common.R.drawable.ic_search),
                    contentDescription =null,
                    modifier = Modifier.size(18.dp)
                )
            }
        )
        Spacer(modifier = Modifier.width(8.dp))

        AppLabel(
            caption = "Cancel",
            style = MaterialTheme.typography.labelMedium,
            color = AppColors.Text2,
            modifier = Modifier.clickable {
                findScreenViewModel._currentNavController.value?.popBackStack()
            })


    }
}

@Composable
fun Display(
    modifier: Modifier=Modifier,
    findScreenViewModel: FindScreenViewModel,
    lazyPagingItems: LazyPagingItems<CharacterModel>
){

    LazyColumn(modifier = modifier
        .fillMaxSize()
        ) {



        items(count = lazyPagingItems.itemCount){
            DrawItem(findScreenViewModel,lazyPagingItems.get(it)!!)
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
fun DrawItem(findScreenViewModel: FindScreenViewModel, data: CharacterModel) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color= getColor(AppColors.ContainerBackground2))
        .height(80.dp)
        .clickable {
            findScreenViewModel._currentNavController.value?.navigate(
                HomeFeatureRoutes.DetailsScreenRoute(
                    data
                )
            )


        }) {

        Row(modifier = Modifier.fillMaxWidth()) {
            LoadNetworkImage(url = data.avatar, scale = ContentScale.Crop, modifier = Modifier.fillMaxHeight().width(80.dp))



            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Center) {

               Box(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 8.dp, end = 8.dp), contentAlignment = Alignment.CenterStart){
                   AppLabel(
                       caption = data.name,
                       style = MaterialTheme.typography.labelMedium,
                       textAlign = TextAlign.Center,
                       color = AppColors.Text3,
                       maxline = 2,

                       )
               }



                HorizontalDivider(modifier = Modifier.fillMaxWidth(),color = Color.Gray, thickness = 0.5.dp)

            }

        }





    }

}











