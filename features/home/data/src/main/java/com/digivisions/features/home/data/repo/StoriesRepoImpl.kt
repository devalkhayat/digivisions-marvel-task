package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.StoriesDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.details.StoryResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.StoriesRepository
import javax.inject.Inject

class StoriesRepoImpl @Inject constructor(private val storiesDataProviders: StoriesDataProviders):
    StoriesRepository {
    override suspend fun getStoryDetails(id: Int): StoryResultModel = storiesDataProviders.getStoryDetails(id).toDomainInstance()

}