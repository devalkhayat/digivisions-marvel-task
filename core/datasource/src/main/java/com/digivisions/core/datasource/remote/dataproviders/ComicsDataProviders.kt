package com.digivisions.core.datasource.remote.dataproviders

import com.digivisions.core.datasource.remote.ApiService
import javax.inject.Inject

class ComicsDataProviders @Inject constructor(private val apiService:ApiService) {
    suspend fun getComicDetails(id:Int)=apiService.getComic(id)
}