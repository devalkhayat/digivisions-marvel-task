package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.character.CharactersResultModel

interface CharactersRepository {
    suspend fun getCharactersInformation(offset:Int?=null): CharactersResultModel
}