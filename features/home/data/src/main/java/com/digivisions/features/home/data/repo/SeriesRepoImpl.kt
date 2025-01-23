package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.SeriesDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.details.SeriesResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.SeriesRepository
import javax.inject.Inject

class SeriesRepoImpl @Inject constructor(private val seriesDataProviders: SeriesDataProviders):
    SeriesRepository {
    override suspend fun getSeriesDetails(id: Int): SeriesResultModel = seriesDataProviders.getSeriesDetails(id).toDomainInstance()

}