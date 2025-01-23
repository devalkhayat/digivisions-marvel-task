package com.digivisions.features.home.ui.screens.home

import com.digivisions.features.home.domain.model.character.CharactersResultModel

data class HomeScreenStateHolder(val isLoading:Boolean=false, val data: CharactersResultModel?=null, val error:String="")