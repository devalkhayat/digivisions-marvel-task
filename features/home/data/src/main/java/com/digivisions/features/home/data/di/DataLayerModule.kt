package com.digivisions.features.home.data.di

import com.digivisions.core.datasource.remote.dataproviders.CharactersDataProviders
import com.digivisions.core.datasource.remote.dataproviders.ComicsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.EventsDataProviders
import com.digivisions.core.datasource.remote.dataproviders.SeriesDataProviders
import com.digivisions.core.datasource.remote.dataproviders.StoriesDataProviders
import com.digivisions.features.home.data.repo.CharacterRepoImpl
import com.digivisions.features.home.data.repo.ComicsRepoImpl
import com.digivisions.features.home.data.repo.EventsRepoImpl
import com.digivisions.features.home.data.repo.SeriesRepoImpl
import com.digivisions.features.home.data.repo.StoriesRepoImpl
import com.digivisions.features.home.domain.repo.CharactersRepository
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.EventsRepository
import com.digivisions.features.home.domain.repo.SeriesRepository
import com.digivisions.features.home.domain.repo.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun provideCharacterRepo(charactersDataProviders: CharactersDataProviders):CharactersRepository= CharacterRepoImpl(charactersDataProviders)

    @Provides
    fun provideComicsRepo(comicsDataProviders: ComicsDataProviders):ComicsRepository=ComicsRepoImpl(comicsDataProviders)

    @Provides
    fun provideEventsRepo(eventsDataProviders: EventsDataProviders): EventsRepository = EventsRepoImpl(eventsDataProviders)

    @Provides
    fun provideStoriesRepo(storiesDataProviders: StoriesDataProviders): StoriesRepository = StoriesRepoImpl(storiesDataProviders)

    @Provides
    fun provideSeriesRepo(seriesDataProviders: SeriesDataProviders): SeriesRepository = SeriesRepoImpl(seriesDataProviders)

}