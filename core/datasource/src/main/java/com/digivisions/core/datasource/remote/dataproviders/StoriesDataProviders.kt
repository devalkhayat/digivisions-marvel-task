package com.digivisions.core.datasource.remote.dataproviders

import com.digivisions.core.datasource.remote.ApiService
import javax.inject.Inject

class StoriesDataProviders @Inject constructor(private val apiService:ApiService) {
    suspend fun getStoryDetails(id:Int)=apiService.getStory(id)
}