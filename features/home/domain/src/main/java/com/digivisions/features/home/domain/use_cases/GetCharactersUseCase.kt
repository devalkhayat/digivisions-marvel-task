package com.digivisions.features.home.domain.use_cases

import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.model.character.CharactersResultModel
import com.digivisions.features.home.domain.repo.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    operator fun invoke(offset:Int?=null) = flow<UiEvent<CharactersResultModel>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(charactersRepository.getCharactersInformation(offset)))
    }.catch() {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}