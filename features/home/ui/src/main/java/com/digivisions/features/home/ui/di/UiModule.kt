package com.digivisions.features.home.ui.di

import com.digivisions.features.home.domain.use_cases.FindCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetCharactersUseCase
import com.digivisions.features.home.domain.use_cases.GetComicDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetEventDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetSeriesDetailsUseCase
import com.digivisions.features.home.domain.use_cases.GetStoryDetailsUseCase
import com.digivisions.features.home.ui.navigation.HomeApi
import com.digivisions.features.home.ui.navigation.HomeApiImpl
import com.digivisions.features.home.ui.screens.details.DetailsScreenViewModel
import com.digivisions.features.home.ui.screens.find.FindScreenViewModel
import com.digivisions.features.home.ui.screens.home.HomeScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideDashboardApi(): HomeApi {
        return HomeApiImpl()
    }
    @Provides
    fun provideHomeScreenViewModel(getCharactersUseCase: GetCharactersUseCase): HomeScreenViewModel {
        return HomeScreenViewModel(getCharactersUseCase)
    }

    @Provides
    fun provideDetailsScreenViewModel(
        getComicDetailsUseCase: GetComicDetailsUseCase,
        getEventDetailsUseCase: GetEventDetailsUseCase,
        getStoryDetailsUseCase: GetStoryDetailsUseCase,
        getSeriesDetailsUseCase: GetSeriesDetailsUseCase,
    ): DetailsScreenViewModel {
        return DetailsScreenViewModel(getComicDetailsUseCase,getEventDetailsUseCase,getStoryDetailsUseCase,getSeriesDetailsUseCase)
    }


    @Provides
    fun provideFindScreenViewModel(findCharactersUseCase: FindCharactersUseCase): FindScreenViewModel {
        return FindScreenViewModel(findCharactersUseCase)
    }

}