package com.digivisions.features.home.domain.use_cases

import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.details.StoryResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.StoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetStoryDetailsUseCase @Inject constructor(private val storiesRepository: StoriesRepository) {

    operator fun invoke(id:Int) = flow<UiEvent<StoryResultModel>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(storiesRepository.getStoryDetails(id)))
    }.catch() {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}