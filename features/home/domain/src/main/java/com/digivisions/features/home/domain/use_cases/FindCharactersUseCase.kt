package com.digivisions.features.home.domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.digivisions.core.common.Constants.BASE_OFFSET
import com.digivisions.core.common.UiEvent
import com.digivisions.features.home.domain.CharactersPagingSource
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.CharactersResultModel
import com.digivisions.features.home.domain.repo.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FindCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    operator fun invoke(startWith:String) = flow<UiEvent<Flow<PagingData<CharacterModel>>>> {
        emit(UiEvent.Loading())
        val items: Flow<PagingData<CharacterModel>> = Pager(config = PagingConfig(pageSize = BASE_OFFSET), pagingSourceFactory = { CharactersPagingSource(charactersRepository,startWith) }).flow
        emit(UiEvent.Success(items))
    }.catch() {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}