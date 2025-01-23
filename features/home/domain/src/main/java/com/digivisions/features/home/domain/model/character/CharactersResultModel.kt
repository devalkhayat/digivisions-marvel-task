package com.digivisions.features.home.domain.model.character

import com.digivisions.features.home.domain.model.DataInfoModel

data class CharactersResultModel(val dataInfo: DataInfoModel, val characters:ArrayList<CharacterModel>)
