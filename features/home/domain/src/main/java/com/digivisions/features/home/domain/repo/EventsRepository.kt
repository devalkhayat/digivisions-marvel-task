package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.details.EventResultModel

interface EventsRepository {
    suspend fun getEventDetails(id:Int): EventResultModel
}