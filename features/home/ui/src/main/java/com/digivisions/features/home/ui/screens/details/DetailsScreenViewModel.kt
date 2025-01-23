package com.digivisions.features.home.ui.screens.details

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
import com.digivisions.features.home.domain.use_cases.GetEventDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetSeriesDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetStoryDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getComicDetailsUseCase: GetComicDetailsUseCase,
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val getStoryDetailsUseCase: GetStoryDetailsUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) : ViewModel() {

    private val _resultInfo = mutableStateOf(DetailsScreenStateHolder<Any>())
    val resultInfo: State<DetailsScreenStateHolder<Any>> get() = _resultInfo

    var _currentItem = mutableStateOf<CharacterModel?>(null)
    var _currentNavController= mutableStateOf<NavHostController?>(null)

    fun getComicDetails(id: Int) {
        viewModelScope.launch {
            getComicDetailsUseCase(id).onEach {
                when(it) {
                    is UiEvent.Loading -> _resultInfo.value =
                        DetailsScreenStateHolder(isLoading = true)

                    is UiEvent.Success -> _resultInfo.value =
                        DetailsScreenStateHolder(data = it.data)

                    is UiEvent.Error -> _resultInfo.value =
                        DetailsScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getEventDetails(id: Int) {
        viewModelScope.launch {
            getEventDetailsUseCase(id).onEach {
                when(it) {
                    is UiEvent.Loading -> _resultInfo.value =
                        DetailsScreenStateHolder(isLoading = true)

                    is UiEvent.Success -> _resultInfo.value =
                        DetailsScreenStateHolder(data = it.data)

                    is UiEvent.Error -> _resultInfo.value =
                        DetailsScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getStoryDetails(id: Int) {
        viewModelScope.launch {
            getStoryDetailsUseCase(id).onEach {
                when(it) {
                    is UiEvent.Loading -> _resultInfo.value =
                        DetailsScreenStateHolder(isLoading = true)

                    is UiEvent.Success -> _resultInfo.value =
                        DetailsScreenStateHolder(data = it.data)

                    is UiEvent.Error -> _resultInfo.value =
                        DetailsScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getSeriesDetails(id: Int) {
        viewModelScope.launch {
            getSeriesDetailsUseCase(id).onEach {
                when(it) {
                    is UiEvent.Loading -> _resultInfo.value =
                        DetailsScreenStateHolder(isLoading = true)

                    is UiEvent.Success -> _resultInfo.value =
                        DetailsScreenStateHolder(data = it.data)

                    is UiEvent.Error -> _resultInfo.value =
                        DetailsScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)
        }
    }


}