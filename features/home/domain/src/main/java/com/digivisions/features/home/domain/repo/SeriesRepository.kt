package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.details.SeriesResultModel

interface SeriesRepository {
    suspend fun getSeriesDetails(id:Int): SeriesResultModel
}