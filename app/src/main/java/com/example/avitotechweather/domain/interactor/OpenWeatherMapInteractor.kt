package com.example.avitotechweather.domain.interactor

import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.domain.entity.WeatherCurrentDTO
import javax.inject.Inject

class OpenWeatherMapInteractor @Inject constructor(private val repository: IOpenWeatherMapRepository) :
    IOpenWeatherMapInteractor {
    override suspend fun getCurrentWeather(cityName: String): Result<WeatherCurrentDTO> {
        return repository.getCurrentWeather(cityName)
    }

    override suspend fun getCurrentGeoWeather(lat: String, lon: String): Result<WeatherCurrentDTO> {
        return repository.getCurrentGeoWeather(lat, lon)
    }

    override suspend fun getDayWeekWeather(city: String, cnt: Int): Result<WeatherAllDTO> {
        return repository.getDayWeekWeather(city, cnt)
    }

    override suspend fun getGeoDayWeekWeather(lat: String, lon: String, cnt: Int): Result<WeatherAllDTO> {
        return repository.getGeoDayWeekWeather(lat, lon, cnt)
    }
}
