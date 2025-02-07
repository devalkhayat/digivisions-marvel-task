package com.digivisions.features.home.ui.screens.home

import androidx.paging.PagingData
import com.digivisions.features.home.domain.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

data class HomeScreenStateHolder(val isLoading:Boolean=false, val data: Flow<PagingData<CharacterModel>>? =null, val error:String="")