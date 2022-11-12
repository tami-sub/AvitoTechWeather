package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.network.OpenWeatherMapApi
import com.example.avitotechweather.data.network.Utils.BASE_URL
import com.example.avitotechweather.domain.entity.CityDTO
import com.example.avitotechweather.domain.entity.WeatherDTO
import retrofit2.Call
import javax.inject.Inject

class OpenWeatherMapRepository
@Inject constructor(private val api: OpenWeatherMapApi )
{
    suspend fun getCityLatLon(cityName: String): Result<List<CityDTO>> {
        return api.getCityLatLon(cityName)
    }

    suspend fun getCurrentWeather(cityName: String): Result<List<WeatherDTO>> {
        return api.getCurrentWeather()
    }
}