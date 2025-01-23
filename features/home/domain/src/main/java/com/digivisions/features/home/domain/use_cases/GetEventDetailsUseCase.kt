package com.digivisions.features.home.domain.use_cases

import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.details.EventResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetEventDetailsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {

    operator fun invoke(id:Int) = flow<UiEvent<EventResultModel>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(eventsRepository.getEventDetails(id)))
    }.catch() {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}