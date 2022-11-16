package com.example.avitotechweather.domain.interactor

import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.domain.entity.WeatherCurrentDTO

interface IOpenWeatherMapInteractor {
    suspend fun getCurrentWeather(cityName: String): Result<WeatherCurrentDTO>

    suspend fun getCurrentGeoWeather(lat: String, lon: String): Result<WeatherCurrentDTO>

    suspend fun getDayWeekWeather(city: String, cnt: Int): Result<WeatherAllDTO>

    suspend fun getGeoDayWeekWeather(lat: String, lon: String, cnt: Int): Result<WeatherAllDTO>
}