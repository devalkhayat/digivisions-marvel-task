package com.digivisions.features.home.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.cachedIn
import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.use_cases.FindCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase):ViewModel() {

    private val _resultInfo= mutableStateOf(HomeScreenStateHolder())
    val resultInfo:State<HomeScreenStateHolder> get()=_resultInfo
    var _currentNavController= mutableStateOf<NavHostController?>(null)



    fun getAll() {
        viewModelScope.launch {

            getCharactersUseCase().onEach {
                when(it){
                    is UiEvent.Loading -> _resultInfo.value= HomeScreenStateHolder(isLoading = true)
                    is UiEvent.Success -> _resultInfo.value= HomeScreenStateHolder(data =it.data?.cachedIn(
                        viewModelScope
                    ))
                    is UiEvent.Error -> _resultInfo.value= HomeScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)

        }
    }



}





