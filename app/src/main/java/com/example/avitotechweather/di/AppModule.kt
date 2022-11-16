package com.example.avitotechweather.di

import com.example.avitotechweather.data.network.CustomInterceptor
import com.example.avitotechweather.data.network.OpenWeatherMapApi
import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import com.example.avitotechweather.utils.Utils.BASE_URL
import com.example.avitotechweather.exception.ResultCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): OpenWeatherMapApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(loggingHttp())
            .build()
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Singleton
    @Provides
    fun loggingHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(CustomInterceptor())
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
//        return retrofit.create(OpenWeatherMapApi::class.java)
//    }

//    @Singleton
//    @Provides
//    fun provideOpenWeatherMapRepository(retrofit: Retrofit): IOpenWeatherMapRepository {
//        return retrofit.create(OpenWeatherMapRepository())
//    }

}