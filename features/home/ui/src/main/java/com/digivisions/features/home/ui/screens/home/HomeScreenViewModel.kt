package com.digivisions.features.home.ui.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.use_cases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase):ViewModel() {

    private val _resultInfo= mutableStateOf(HomeScreenStateHolder())
    val resultInfo:State<HomeScreenStateHolder> get()=_resultInfo
    var _currentNavController= mutableStateOf<NavHostController?>(null)

    fun getAll(offset:Int?=null) {
        viewModelScope.launch {

            getCharactersUseCase(offset).onEach {
                when(it){
                    is UiEvent.Loading -> _resultInfo.value= HomeScreenStateHolder(isLoading = true)
                    is UiEvent.Success -> _resultInfo.value= HomeScreenStateHolder(data=it.data)
                    is UiEvent.Error -> _resultInfo.value= HomeScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)

        }
    }


}





