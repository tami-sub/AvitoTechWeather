package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.network.OpenWeatherMapApi
import com.example.avitotechweather.domain.entity.CityDTO
import retrofit2.Call
import javax.inject.Inject

class OpenWeatherMapRepository
@Inject constructor(private val api: OpenWeatherMapApi )
{

//    suspend fun getCityLatLon(cityName: String): Call<CityDTO> {
//        return api.getCityLatLon(cityName)
//    }
}