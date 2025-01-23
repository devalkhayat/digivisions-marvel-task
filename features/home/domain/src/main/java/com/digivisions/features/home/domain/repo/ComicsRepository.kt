package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.details.ComicResultModel

interface ComicsRepository {
    suspend fun getComicDetails(id:Int): ComicResultModel
}