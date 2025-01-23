package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.EventsDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.details.EventResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.EventsRepository
import javax.inject.Inject

class EventsRepoImpl @Inject constructor(private val evnetsDataProviders: EventsDataProviders):
    EventsRepository {
    override suspend fun getEventDetails(id: Int): EventResultModel = evnetsDataProviders.getEventDetails(id).toDomainInstance()
}