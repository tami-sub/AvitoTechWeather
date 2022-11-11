package com.example.avitotechweather.data.repository

import com.example.avitotechweather.domain.entity.CityDTO

interface IOpenWeatherMapRepository {
    suspend fun createRole(cityName: String): CityDTO
}