package com.digivisions.features.home.ui.screens.details

import com.digivisions.features.home.domain.model.details.ComicResultModel

data class DetailsScreenStateHolder<T>(val isLoading:Boolean=false, val data: T?=null, val error:String="")

