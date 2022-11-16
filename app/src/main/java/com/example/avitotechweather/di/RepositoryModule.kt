package com.example.avitotechweather.di

import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun openWeatherMapRepository(openWeatherMapRepository: OpenWeatherMapRepository): IOpenWeatherMapRepository
}