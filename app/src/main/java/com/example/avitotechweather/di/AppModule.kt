package com.example.avitotechweather.di

import com.example.avitotechweather.data.network.OpenWeatherMapApi
import com.example.avitotechweather.data.network.Utils.BASE_URL
import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getOpenWeatherMapInstance(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    @Singleton
    fun getOpenWeatherMapRepositoryInstance(retrofit: Retrofit): IOpenWeatherMapRepository {
        return retrofit.create(IOpenWeatherMapRepository::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}