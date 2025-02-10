package com.digivisions.features.home.ui.screens.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.digivisions.features.home.domain.use_cases.GetComicDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.use_cases.GetEventDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetSeriesDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetStoryDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getComicDetailsUseCase: GetComicDetailsUseCase,
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val getStoryDetailsUseCase: GetStoryDetailsUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) : ViewModel() {

    val currentItem = mutableStateOf<CharacterModel?>(null)
    val currentNavController = mutableStateOf<NavHostController?>(null)
    val comicsActionState = mutableStateOf<ComicsStateHolder>(ComicsStateHolder.Loading)
    val eventsActionState = mutableStateOf<EventStateHolder>(EventStateHolder.Loading)
    val storiesActionState = mutableStateOf<StoriesStateHolder>(StoriesStateHolder.Loading)
    val seriesActionState = mutableStateOf<SeriesStateHolder>(SeriesStateHolder.Loading)

    fun assign(model: CharacterModel) {
        viewModelScope.launch {
            currentItem.value = model

            launch { loadComics() }
            launch { loadSeries() }
            launch { loadStories() }
            launch { loadEvents() }


        }

    }

    private suspend fun loadComics() {
        viewModelScope.launch {

            currentItem.value!!.comicList.forEach { i ->

                getComicDetailsUseCase(i.id).filter { item -> (item as UiEvent).data != null }
                    .collect {
                        i.url = it.data?.comicDetailsModel?.avatar
                        i.url_large = it.data?.comicDetailsModel?.full_image
                    }
            }
            comicsActionState.value = ComicsStateHolder.Finish(currentItem.value!!.comicList)
        }
    }

    private suspend fun loadEvents() {
        viewModelScope.launch {

            currentItem.value!!.eventList.forEach { i ->

                getEventDetailsUseCase(i.id).filter { item -> (item as UiEvent).data != null }
                    .collect {
                        i.url = it.data?.eventDetailsModel?.avatar
                        i.url_large = it.data?.eventDetailsModel?.full_image
                    }
            }
            eventsActionState.value = EventStateHolder.Finish(currentItem.value!!.eventList)
        }
    }

    private suspend fun loadStories() {
        viewModelScope.launch {
            currentItem.value!!.storyList.forEach { i ->
                getStoryDetailsUseCase(i.id).filter { item -> (item as UiEvent).data != null }
                    .collect {
                        i.url = it.data?.storyDetailsModel?.avatar
                        i.url_large = it.data?.storyDetailsModel?.full_image

                    }
            }
            storiesActionState.value = StoriesStateHolder.Finish(currentItem.value!!.storyList)
        }
    }

    private suspend fun loadSeries() {
        viewModelScope.launch {

            currentItem.value!!.seriesList.forEach { i ->

                getSeriesDetailsUseCase(i.id).filter { item -> (item as UiEvent).data != null }
                    .collect {
                        i.url = it.data?.seriesDetailsModel?.avatar
                        i.url_large = it.data?.seriesDetailsModel?.full_image
                    }
            }
            seriesActionState.value = SeriesStateHolder.Finish(currentItem.value!!.seriesList)
        }
    }


}