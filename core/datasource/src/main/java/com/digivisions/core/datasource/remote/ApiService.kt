package com.digivisions.core.datasource.remote


import com.digivisions.core.datasource.remote.model.responses.CharactersResponse
import com.digivisions.core.datasource.remote.model.responses.ComicDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.EventDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.SeriesDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.StoryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset:Int?=null): CharactersResponse
    @GET("v1/public/comics/{id}")
    suspend fun getComic(@Path("id") id :Int): ComicDetailsResponse
    @GET("v1/public/events/{id}")
    suspend fun getEvent(@Path("id") id :Int): EventDetailsResponse
    @GET("v1/public/stories/{id}")
    suspend fun getStory(@Path("id") id :Int): StoryDetailsResponse
    @GET("v1/public/series/{id}")
    suspend fun getSeries(@Path("id") id :Int): SeriesDetailsResponse

}