package com.example.avitotechweather.domain.interactors

import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import com.example.avitotechweather.domain.entity.CityDTO
import retrofit2.Call
import javax.inject.Inject

//class OpenWeatherMapInteractor @Inject constructor(
//    private val openWeatherMapRepository: IOpenWeatherMapRepository)
//: IOpenWeatherMapInteractor {
//
//    override suspend fun getCityLatLon(cityName: String): Call<CityDTO> =
//        openWeatherMapRepository.getCityLatLon(cityName)
//}