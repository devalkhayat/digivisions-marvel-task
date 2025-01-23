package com.digivisions.core.datasource.remote.dataproviders

import com.digivisions.core.datasource.remote.ApiService
import javax.inject.Inject

class SeriesDataProviders @Inject constructor(private val apiService:ApiService) {
    suspend fun getSeriesDetails(id:Int)=apiService.getSeries(id)
}