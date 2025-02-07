package com.digivisions.core.datasource.remote.dataproviders

import com.digivisions.core.datasource.remote.ApiService
import javax.inject.Inject

class CharactersDataProviders @Inject constructor(private val apiService:ApiService) {
    suspend fun getCharacters(offset:Int?=null, limit:Int?=null,nameStartsWith:String?=null)=apiService.getCharacters(offset=offset,limit=limit,nameStartsWith=nameStartsWith)
}