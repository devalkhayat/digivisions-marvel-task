package com.digivisions.features.home.ui.screens.find

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.cachedIn
import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.use_cases.FindCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindScreenViewModel @Inject constructor( private val findCharactersUseCase: FindCharactersUseCase):ViewModel() {

    private val _resultInfo= mutableStateOf(FindScreenStateHolder())
    val resultInfo:State<FindScreenStateHolder> get()=_resultInfo
    var _currentNavController= mutableStateOf<NavHostController?>(null)

    val userInputFlow= MutableStateFlow("")

    fun start(startWith:String){
        viewModelScope.launch {
            userInputFlow.debounce(500).collect{
                find(startWith)
            }
        }

    }
    private fun find(startWith:String) {
        viewModelScope.launch {

            findCharactersUseCase(startWith).onEach {
                when(it){
                    is UiEvent.Loading -> _resultInfo.value= FindScreenStateHolder(isLoading = true)
                    is UiEvent.Success -> _resultInfo.value= FindScreenStateHolder(data =it.data?.cachedIn(
                        viewModelScope
                    ))
                    is UiEvent.Error -> _resultInfo.value= FindScreenStateHolder(error = it.message.toString())
                }
            }.launchIn(viewModelScope)

        }
    }


}





