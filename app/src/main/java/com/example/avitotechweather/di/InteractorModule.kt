package com.example.avitotechweather.di

import com.example.avitotechweather.domain.interactor.IOpenWeatherMapInteractor
import com.example.avitotechweather.domain.interactor.OpenWeatherMapInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Singleton
    @Binds
    abstract fun openWeatherMapInteractor(openWeatherMapInteractor: OpenWeatherMapInteractor): IOpenWeatherMapInteractor

}