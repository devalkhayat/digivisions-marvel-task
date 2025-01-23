package com.digivisions.features.home.ui.screens.details

import android.util.Log
import android.widget.ImageButton
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.digivisions.core.common.ModelType
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.LoadNetworkImage
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.ui.R
import com.digivisions.features.home.ui.screens.home.HomeScreenViewModel

@Composable
fun DetailsScreen(navHostController: NavHostController, currentItem: CharacterModel,detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()){

    LaunchedEffect(true) {
        detailsScreenViewModel._currentItem.value=currentItem
        detailsScreenViewModel._currentNavController.value=navHostController

    }

   val x= detailsScreenViewModel._currentItem.value



    Column(modifier = Modifier
        .fillMaxSize()
        .paint(painterResource(id = com.digivisions.core.common.R.drawable.background), contentScale = ContentScale.FillBounds)
        .verticalScroll(rememberScrollState())) {
        x?.let {
            Header(it.avatar){
                navHostController.popBackStack()
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp,end=8.dp)) {
                InfoSection(name = it.name, description = it.description)
                Spacer(modifier = Modifier.height(32.dp))

                detailsScreenViewModel._currentItem.value?.let {
                    if (it.comicList.size > 0)
                        ListSection(
                            modelType = ModelType.Comic,
                            stringResource(com.digivisions.core.common.R.string.label_comics),
                            detailsScreenViewModel
                        )
                    if (it.seriesList.size > 0) {
                        Spacer(modifier = Modifier.height(32.dp))
                        ListSection(
                            modelType = ModelType.Series,
                            stringResource(com.digivisions.core.common.R.string.label_series),
                            detailsScreenViewModel
                        )
                    }
                    if (it.storyList.size > 0) {
                        Spacer(modifier = Modifier.height(32.dp))
                        ListSection(
                            modelType = ModelType.Story,
                            stringResource(com.digivisions.core.common.R.string.label_stories),
                            detailsScreenViewModel
                        )
                    }
                    if (it.eventList.size > 0) {
                        Spacer(modifier = Modifier.height(32.dp))
                        ListSection(
                            modelType = ModelType.Event,
                            stringResource(com.digivisions.core.common.R.string.label_events),
                            detailsScreenViewModel
                        )
                    }
                }



                Spacer(modifier = Modifier.height(32.dp))
                RelatedLinksSection()
                Spacer(modifier = Modifier.height(32.dp))
            }


        }


    }


}

@Composable
fun Header(url:String,onPressBack:()->Unit){
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)){
        LoadNetworkImage(url=url, modifier = Modifier.fillMaxSize(), scale = ContentScale.FillBounds)
        IconButton(onClick = {onPressBack()}, modifier = Modifier.align(alignment = Alignment.TopStart)) {
            Icon(painterResource(com.digivisions.core.common.R.drawable.ic_arrow_left), contentDescription = null,modifier = Modifier.size(24.dp))
        }
    }

}

@Composable
fun InfoSection(name:String,description:String){
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxWidth()) {
            InfoLabel(caption = stringResource(com.digivisions.core.common.R.string.label_name), value = name)
            Spacer(modifier = Modifier.height(8.dp))
            InfoLabel(caption = stringResource(com.digivisions.core.common.R.string.label_description), value = description)

        }
    }
}
@Composable
fun InfoLabel(caption:String,value:String){

    Column(modifier = Modifier.fillMaxWidth()) {
        AppLabel(caption = caption, style = MaterialTheme.typography.labelMedium,color=AppColors.Text2)
        Spacer(modifier = Modifier.height(8.dp))
        AppLabel(caption = value, style = MaterialTheme.typography.labelSmall,color=AppColors.Text3)

    }
}

@Composable
fun RelatedLinksSection() {

    Column(modifier = Modifier.fillMaxWidth()) {
        AppLabel(
            caption = stringResource(com.digivisions.core.common.R.string.label_related_link),
            style = MaterialTheme.typography.labelMedium,
            color = AppColors.Text2
        )
        Spacer(modifier = Modifier.height(12.dp))

        Link(stringResource(com.digivisions.core.common.R.string.label_details))
        Spacer(modifier = Modifier.height(12.dp))
        Link(stringResource(com.digivisions.core.common.R.string.label_wiki))
        Spacer(modifier = Modifier.height(12.dp))
        Link(stringResource(com.digivisions.core.common.R.string.label_comiclink))
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun Link(caption:String){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        AppLabel(caption = caption, style = MaterialTheme.typography.labelLarge,color=AppColors.Text3)

        Icon(painterResource(com.digivisions.core.common.R.drawable.ic_arrow_right), tint = getColor(AppColors.Tint2), contentDescription = null, modifier = Modifier.size(24.dp))
    }
}

@Composable
fun ListSection(modelType: ModelType,caption: String,detailsScreenViewModel:DetailsScreenViewModel) {

    var url by remember { mutableStateOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_dSygMWbKQFzgP20rLq6crx3itm6mnQ5hcA&s") }

    Column(modifier = Modifier.fillMaxWidth()) {
        AppLabel(
            caption = caption.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = AppColors.Text2
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            when(modelType){
                ModelType.Comic -> {
                    detailsScreenViewModel._currentItem.value?.comicList?.forEach {

                        item { GenerateItem(detailsScreenViewModel,url = url, caption = it.name)  }

                    }
                }
                ModelType.Story -> {
                    detailsScreenViewModel._currentItem.value?.storyList?.forEach {

                        item { GenerateItem(detailsScreenViewModel,url = url, caption = it.name)  }

                    }
                }
                ModelType.Series -> {
                    detailsScreenViewModel._currentItem.value?.seriesList?.forEach {

                        item { GenerateItem(detailsScreenViewModel,url =url, caption = it.name)  }

                    }
                }
                ModelType.Event -> {

                    detailsScreenViewModel._currentItem.value?.eventList?.forEach {

                        item { GenerateItem(detailsScreenViewModel,url = url, caption = it.name)  }

                    }
                }
            }




        }
    }
}
@Composable
fun GenerateItem(detailsScreenViewModel:DetailsScreenViewModel,url: String, caption: String) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
    ) {

        Box(modifier = Modifier.size(width = 120.dp, height = 200.dp).clickable {
            detailsScreenViewModel._currentNavController.value?.navigate(HomeFeatureRoutes.PreviewScreenRoute(url,caption))
        }) {
            AsyncImage(
                model = url,
                contentDescription = null,
                onLoading = {

                },
                onError = {
                    Log.d("neo2020", "GenerateItem: ${it.result.throwable.message}")
                },
                /*placeholder = painterResource(com.digivisions.core.common.R.drawable.ic_humidity),
                error = painterResource(com.digivisions.core.common.R.drawable.ic_wind),*/
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )


        }
        Spacer(modifier = Modifier.height(8.dp))
        AppLabel(
            caption = caption,
            style = MaterialTheme.typography.labelSmall,
            color = AppColors.Text3,
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}





/*
 val getUrlRememberCoroutine= rememberCoroutineScope()
if (i.url.isNullOrBlank()){
    getUrlRememberCoroutine.launch {
        detailsScreenViewModel.getComicDetails(i.id)
        val rs=detailsScreenViewModel.resultInfo
        if(!rs.value.isLoading && rs.value.error.isNotBlank()){
            url= (detailsScreenViewModel.resultInfo.value.data as ComicModel).url.toString()
            Log.d("neo88", "ListSection: $url")
        }else{
            Log.d("neo88", "ListSection:${rs.value.error.toString()}")
        }
    }
}*/


