package com.example.avitotechweather.data.network

import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.domain.entity.WeatherCurrentDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/forecast")
    suspend fun getDayWeekWeather(
        @Query("q") city: String,
        @Query("cnt") cnt: Int,
        @Query("units") celsius: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Result<WeatherAllDTO>

    @GET("data/2.5/forecast")
    suspend fun getGeoDayWeekWeather(
        @Query("lat") lat: String,
        @Query("lon") lan: String,
        @Query("cnt") cnt: Int,
        @Query("units") celsius: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Result<WeatherAllDTO>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") celsius: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Result<WeatherCurrentDTO>

    @GET("data/2.5/weather")
    suspend fun getCurrentGeoWeather(
        @Query("lat") lat: String,
        @Query("lon") lan: String,
        @Query("units") celsius: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Result<WeatherCurrentDTO>
}