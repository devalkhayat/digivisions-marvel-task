package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.details.StoryResultModel

interface StoriesRepository {
    suspend fun getStoryDetails(id:Int): StoryResultModel
}