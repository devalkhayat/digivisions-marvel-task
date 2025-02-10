package com.digivisions.features.home.ui.screens.details

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.util.Log
import android.widget.ImageButton
import android.widget.Space
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.digivisions.core.common.components.CircleLoading
import com.digivisions.core.common.components.LoadNetworkImage
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor
import com.digivisions.core.navigation.HomeFeatureRoutes
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import com.digivisions.features.home.domain.model.character.StoriesModel


@Composable
fun DetailsScreen(navHostController: NavHostController, currentItem: CharacterModel,detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()){

    LaunchedEffect(currentItem) {
        detailsScreenViewModel.assign(currentItem)
        detailsScreenViewModel.currentNavController.value=navHostController
    }

    val x by detailsScreenViewModel.currentItem
    val comics by detailsScreenViewModel.comicsActionState
    val events by detailsScreenViewModel.eventsActionState
    val stories by detailsScreenViewModel.storiesActionState
    val series by detailsScreenViewModel.seriesActionState

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = com.digivisions.core.common.R.drawable.background),
            contentScale = ContentScale.FillBounds
        )
        .verticalScroll(rememberScrollState())) {


            x?.let { i->
                Log.d("neo8585", "DetailsScreen: ${i.full_image}")
                Header(i.full_image){
                    navHostController.popBackStack()
                }
                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)) {

                    InfoSection(name = i.name, description = i.description)

                    if(i.comicList.size >0) {
                        Spacer(modifier = Modifier.height(32.dp))

                        when(comics){
                           is ComicsStateHolder.Finish ->
                                ListSection(
                                    stringResource(com.digivisions.core.common.R.string.label_comics),
                                    (comics as ComicsStateHolder.Finish).data,
                                    detailsScreenViewModel
                                )

                            ComicsStateHolder.Loading -> ShowLoading()
                        }

                    }
                    if (i.seriesList.size > 0) {
                          Spacer(modifier = Modifier.height(32.dp))
                        when(series){
                            is SeriesStateHolder.Finish ->
                                ListSection(
                                    stringResource(com.digivisions.core.common.R.string.label_series),
                                    (series as SeriesStateHolder.Finish).data,
                                    detailsScreenViewModel
                                )

                            SeriesStateHolder.Loading -> ShowLoading()
                        }

                      }
                    if (i.storyList.size > 0) {
                          Spacer(modifier = Modifier.height(32.dp))

                        when(stories){
                            is StoriesStateHolder.Finish ->
                                ListSection(
                                    stringResource(com.digivisions.core.common.R.string.label_stories),
                                    (stories as StoriesStateHolder.Finish).data,
                                    detailsScreenViewModel
                                )

                            StoriesStateHolder.Loading -> ShowLoading()
                        }
                      }
                    if (i.eventList.size > 0) {
                          Spacer(modifier = Modifier.height(32.dp))
                        when(events){
                            is EventStateHolder.Finish ->
                                ListSection(
                                    stringResource(com.digivisions.core.common.R.string.label_events),
                                    (events as EventStateHolder.Finish).data,
                                    detailsScreenViewModel
                                )

                            EventStateHolder.Loading -> ShowLoading()
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
fun <T> ListSection(caption: String,data:ArrayList<T>,detailsScreenViewModel: DetailsScreenViewModel) {

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

            for( i in data){
                if(i is ComicModel){
                    item {GenerateItem(ModelType.Comic, i.url, i.name,detailsScreenViewModel) }
                }
                if(i is SeriesModel){
                    item {GenerateItem(ModelType.Series, i.url, i.name,detailsScreenViewModel) }
                }
                if(i is StoriesModel){
                    item {GenerateItem(ModelType.Story, i.url, i.name,detailsScreenViewModel) }
                }
                if(i is EventsModel){
                    item {GenerateItem( ModelType.Event,i.url, i.name,detailsScreenViewModel) }
                }
            }





        }
    }
}


@Composable
fun GenerateItem(type: ModelType, url:String?, caption: String, detailsScreenViewModel: DetailsScreenViewModel) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
    ) {

        Box(modifier = Modifier
            .size(width = 120.dp, height = 200.dp)
            .clickable {

                detailsScreenViewModel.currentNavController.value?.let {

                    when(type){
                        ModelType.Comic ->  it.navigate(HomeFeatureRoutes.PreviewScreenRoute(comicList=detailsScreenViewModel.currentItem.value?.comicList!!))
                        ModelType.Story ->  it.navigate(HomeFeatureRoutes.PreviewScreenRoute(storiesList=detailsScreenViewModel.currentItem.value?.storyList!!))
                        ModelType.Series -> it.navigate(HomeFeatureRoutes.PreviewScreenRoute(seriesList=detailsScreenViewModel.currentItem.value?.seriesList!!))
                        ModelType.Event ->  it.navigate(HomeFeatureRoutes.PreviewScreenRoute(eventList=detailsScreenViewModel.currentItem.value?.eventList!!))
                    }

                }



            }) {
            AsyncImage(
                model =  url,
                contentDescription = null,
                onLoading = {

                },
                onError = {
                    Log.d("neo2020", "GenerateItem: ${it.result.throwable.message}")
                },
                error = painterResource(com.digivisions.core.common.R.drawable.background_image_error),

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

@Composable
fun ShowLoading(){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column {
            CircleLoading()
            Spacer(modifier = Modifier.height(8.dp))
            AppLabel(caption = "loading...", style = MaterialTheme.typography.labelSmall,color=AppColors.Text3)
        }
    }
}

/*******************************************************************************************/
/*******************************************************************************************/

@Composable
fun Header(url:String,onPressBack:()->Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)){
        LoadNetworkImage(url=url, modifier = Modifier.fillMaxSize(), scale = ContentScale.Crop)
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
