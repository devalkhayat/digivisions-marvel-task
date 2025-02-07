package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.CharactersDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.CharactersResultModel
import com.digivisions.features.home.domain.repo.CharactersRepository
import kotlinx.coroutines.delay
import okio.IOException
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(private val charactersDataProviders: CharactersDataProviders) : CharactersRepository{

    override suspend fun getCharactersInformation(offset: Int?, limit:Int?,nameStartsWith:String?): ArrayList<CharacterModel> {
        delay(1000L)
        return charactersDataProviders.getCharacters(offset=offset,limit=limit, nameStartsWith =nameStartsWith ).toDomainInstance()
    }

}