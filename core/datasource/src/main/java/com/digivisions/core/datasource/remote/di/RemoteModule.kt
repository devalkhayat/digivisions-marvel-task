package com.digivisions.core.datasource.remote.di

import com.digivisions.core.common.Constants
import com.digivisions.core.datasource.remote.ApiService
import com.digivisions.core.datasource.remote.dataproviders.CharactersDataProviders
import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.EventsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.SeriesDataProviders
import com.digivisions.core.datasource.remote.dataproviders.StoriesDataProviders
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Provides
    fun provideApiService(): ApiService {

            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }.addInterceptor { chain ->
                    var request: Request = chain.request()
                    val url: HttpUrl = request.url.newBuilder().addQueryParameter("apikey", Constants.API_KEY).addQueryParameter("hash",Constants.HASH_KEY).addQueryParameter("ts",Constants.TIMESTAMP_KEY).build()
                    request = request.newBuilder().url(url).build()
                     chain.proceed(request)
                }
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return   Retrofit.Builder().apply {
                baseUrl(Constants.BASE_URL)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.client(okHttpClient).build().create(ApiService::class.java)




    }

    @Provides
    fun provideCharactersDataProvider(apiService: ApiService):CharactersDataProviders{
        return CharactersDataProviders(apiService)
    }

    @Provides
    fun provideComicsDataProvider(apiService: ApiService):ComicsDataProviders{
        return ComicsDataProviders(apiService)
    }

    @Provides
    fun provideEventsDataProvider(apiService: ApiService):EventsDataProviders{
        return EventsDataProviders(apiService)
    }

    @Provides
    fun provideStoriesDataProvider(apiService: ApiService):StoriesDataProviders{
        return StoriesDataProviders(apiService)
    }

    @Provides
    fun provideSeriesDataProvider(apiService: ApiService):SeriesDataProviders{
        return SeriesDataProviders(apiService)
    }

}