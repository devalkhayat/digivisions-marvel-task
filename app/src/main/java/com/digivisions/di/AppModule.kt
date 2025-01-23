package com.digivisions.marvelcharacters.di

import com.digivisions.marvelcharacters.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.digivisions.features.home.ui.navigation.HomeApi

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Provides
    fun provideNavigationProvider(homeApi: HomeApi
    ): NavigationProvider {
        return NavigationProvider(homeApi)
    }
}