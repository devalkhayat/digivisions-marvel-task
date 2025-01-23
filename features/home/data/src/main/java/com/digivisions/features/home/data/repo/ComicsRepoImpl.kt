package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import javax.inject.Inject

class ComicsRepoImpl @Inject constructor(private val comicsDataProviders: ComicsDataProviders):ComicsRepository {
    override suspend fun getComicDetails(id: Int): ComicResultModel = comicsDataProviders.getComicDetails(id).toDomainInstance()

}