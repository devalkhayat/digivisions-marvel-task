package com.digivisions.features.home.ui.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.CircleLoading
import com.digivisions.core.common.components.CustomPagerIndicatorFirstTry
import com.digivisions.core.common.components.LoadNetworkImage
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor
import com.digivisions.features.home.domain.model.character.ComicModel

@Composable
fun PreviewScreen(navHostController: NavHostController,comicsList:ArrayList<ComicModel>){


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(color = getColor(AppColors.PopupBackground))) {

        IconButton(onClick = {navHostController.popBackStack()},modifier = Modifier.align(alignment = Alignment.End)) {
            Icon(painterResource(com.digivisions.core.common.R.drawable.ic_close), tint = getColor(AppColors.Tint2), contentDescription = null, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))



        val pagerState = rememberPagerState { comicsList.size }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().weight(1f), pageSpacing = 8.dp) { page ->
            with( comicsList.get(page)){
                Log.i("neo8585", "PreviewScreen: ${this.name}")
                GenerateItem(this.url_large!!,this.name)
            }


        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

            CustomPagerIndicatorFirstTry(pagerState, indicatorColor = AppColors.CircleProgress)
        }


        Spacer(modifier = Modifier.height(25.dp))

    }



}

sealed class DownloadingImage{
    data object Loading:DownloadingImage()
    data object Success:DownloadingImage()
    data object Error:DownloadingImage()
}

@Composable
fun GenerateItem(url: String,name: String){
var currentState by remember { mutableStateOf<DownloadingImage>(DownloadingImage.Loading) }


    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center){

            AsyncImage(
                model = url,
                contentDescription = null,
                onError = {
                    currentState=DownloadingImage.Error
                },
                onLoading = {
                    currentState=DownloadingImage.Loading
                },
                onSuccess = {
                    currentState=DownloadingImage.Success
                },
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )

            when(currentState){
                DownloadingImage.Error -> {}
                DownloadingImage.Loading -> CircleLoading()
                DownloadingImage.Success -> {}
            }


        }





        Spacer(modifier = Modifier.height(25.dp))

        AppLabel(caption = name, style = MaterialTheme.typography.labelSmall, color= AppColors.Text3,modifier = Modifier.padding(start = 20.dp, end = 20.dp).align(alignment = Alignment.CenterHorizontally))

    }

}