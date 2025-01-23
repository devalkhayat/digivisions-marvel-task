package com.digivisions.features.home.domain.use_cases

import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.repo.ComicsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetComicDetailsUseCase @Inject constructor(private val comicsRepository: ComicsRepository) {

    operator fun invoke(id:Int) = flow<UiEvent<ComicResultModel>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(comicsRepository.getComicDetails(id)))
    }.catch() {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}