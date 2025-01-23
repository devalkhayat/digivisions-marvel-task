package com.digivisions.features.home.data.repo

import com.digivisions.core.datasource.remote.dataproviders.CharactersDataProviders
import com.digivisions.features.home.data.mappers.toDomainInstance
import com.digivisions.features.home.domain.model.character.CharactersResultModel
import com.digivisions.features.home.domain.repo.CharactersRepository
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(private val charactersDataProviders: CharactersDataProviders) : CharactersRepository{

    override suspend fun getCharactersInformation(offset: Int?): CharactersResultModel = charactersDataProviders.getCharacters(offset=offset).toDomainInstance()

}