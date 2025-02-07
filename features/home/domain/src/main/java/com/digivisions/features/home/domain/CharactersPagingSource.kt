package com.digivisions.features.home.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.digivisions.core.common.Constants.BASE_LIMIT
import com.digivisions.core.common.Constants.BASE_OFFSET
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.repo.CharactersRepository
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(private val charactersRepository: CharactersRepository,private val keyword:String?=null): PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
      return  try {

          val currentPage=params.key?:0
          val _data: ArrayList<CharacterModel>
          if(keyword.isNullOrEmpty()){
              _data=charactersRepository.getCharactersInformation(offset = currentPage, limit = BASE_LIMIT)
          }else{
              _data=charactersRepository.getCharactersInformation(offset = currentPage, limit = BASE_LIMIT, nameStartsWith = keyword)
          }

          LoadResult.Page(
              data=_data,
              nextKey = if(_data.isNotEmpty()) currentPage + BASE_OFFSET else null,
              prevKey =null //if (currentPage > 0) currentPage - 20 else null


          )

      }catch (e:Exception){
          return LoadResult.Error(e)
      }
    }
}