package com.digivisions.features.home.domain.repo

import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.CharactersResultModel

interface CharactersRepository {
    suspend fun getCharactersInformation(offset:Int?=null, limit:Int?=null,nameStartsWith:String?=null): ArrayList<CharacterModel>

}