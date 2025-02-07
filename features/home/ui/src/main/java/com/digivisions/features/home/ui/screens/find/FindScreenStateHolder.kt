package com.digivisions.features.home.ui.screens.find

import androidx.paging.PagingData
import com.digivisions.features.home.domain.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

data class FindScreenStateHolder(val ideal:Boolean=true,val isLoading:Boolean=false, val data: Flow<PagingData<CharacterModel>>? =null, val error:String="")