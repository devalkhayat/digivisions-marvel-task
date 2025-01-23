package com.digivisions.core.datasource.remote.dataproviders

import com.digivisions.core.datasource.remote.ApiService
import javax.inject.Inject

class EventsDataProviders @Inject constructor(private val apiService:ApiService) {
    suspend fun getEventDetails(id:Int)=apiService.getEvent(id)
}