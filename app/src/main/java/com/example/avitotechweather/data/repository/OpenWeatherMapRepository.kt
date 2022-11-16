package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.network.OpenWeatherMapApi
import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.domain.entity.WeatherCurrentDTO
import javax.inject.Inject

class OpenWeatherMapRepository
@Inject constructor(private val api: OpenWeatherMapApi) : IOpenWeatherMapRepository {
    override suspend fun getCurrentWeather(cityName: String): Result<WeatherCurrentDTO> {
        return api.getCurrentWeather(cityName)
    }

    override suspend fun getCurrentGeoWeather(lat: String, lon: String): Result<WeatherCurrentDTO> {
        return api.getCurrentGeoWeather(lat, lon)
    }

    override suspend fun getDayWeekWeather(city: String, cnt: Int): Result<WeatherAllDTO> {
        return api.getDayWeekWeather(city, cnt)
    }

    override suspend fun getGeoDayWeekWeather(
        lat: String,
        lon: String,
        cnt: Int,
    ): Result<WeatherAllDTO> {
        return api.getGeoDayWeekWeather(lat, lon, cnt)
    }
}