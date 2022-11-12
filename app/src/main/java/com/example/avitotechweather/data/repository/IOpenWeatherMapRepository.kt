package com.example.avitotechweather.data.repository

import com.example.avitotechweather.domain.entity.CityDTO
import retrofit2.Call

interface IOpenWeatherMapRepository {
    suspend fun getCityLatLon(cityName: String): Call<CityDTO>
}