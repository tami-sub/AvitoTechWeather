package com.example.avitotechweather.data.repository

import com.example.avitotechweather.domain.entity.CityDTO

class OpenWeatherMapRepository : IOpenWeatherMapRepository {

    override suspend fun createRole(cityName: String): CityDTO {
        return
    }
}