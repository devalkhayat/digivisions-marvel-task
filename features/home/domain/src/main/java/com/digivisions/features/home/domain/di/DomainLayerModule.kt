package com.digivisions.features.home.domain.di

import com.digivisions.features.home.domain.CharactersPagingSource
import com.digivisions.features.home.domain.repo.CharactersRepository
import com.digivisions.features.home.domain.repo.ComicsRepository
import com.digivisions.features.home.domain.repo.EventsRepository
import com.digivisions.features.home.domain.repo.SeriesRepository
import com.digivisions.features.home.domain.repo.StoriesRepository
import com.digivisions.features.home.domain.use_cases.FindCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetComicDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetEventDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetSeriesDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetStoryDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {



    @Provides
    fun provideGetCharactersInfoUseCase(charactersRepository: CharactersRepository):GetCharactersUseCase= GetCharactersUseCase(charactersRepository)


    @Provides
    fun provideFindCharactersInfoUseCase(charactersRepository: CharactersRepository): FindCharactersUseCase = FindCharactersUseCase(charactersRepository)

    @Provides
    fun provideGetComicDetailsUseCase(comicsRepository: ComicsRepository):GetComicDetailsUseCase=GetComicDetailsUseCase(comicsRepository)


    @Provides
    fun provideGetEventDetailsUseCase(eventsRepository: EventsRepository): GetEventDetailsUseCase =GetEventDetailsUseCase(eventsRepository)


    @Provides
    fun provideGetStoryDetailsUseCase(storiesRepository: StoriesRepository): GetStoryDetailsUseCase =GetStoryDetailsUseCase(storiesRepository)


    @Provides
    fun provideGetSeriesDetailsUseCase(seriesRepository: SeriesRepository): GetSeriesDetailsUseCase =GetSeriesDetailsUseCase(seriesRepository)


}