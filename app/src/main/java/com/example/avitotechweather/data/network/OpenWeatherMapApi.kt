package com.example.avitotechweather.data.network

import com.example.avitotechweather.data.network.Utils.API_KEY
import com.example.avitotechweather.domain.entity.CityDTO
import com.example.avitotechweather.domain.entity.SuccessDTO
import com.example.avitotechweather.domain.entity.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("geo/1.0/direct")
    suspend fun getCityLatLon(
        @Query("q") city: String,
        @Query("appid") appId: String = API_KEY
    ): List<CityDTO>

//    @GET("v1/current.json?key=f16f2a05abcd48c985d144600221211&q=London")
//    suspend fun getCurrentWeather(
////        @Query("key") appId: String = API_KEY,
////        @Query("q") city: String
////
//    ): List<WeatherDTO>

//    @GET("v1/current.json")
//    suspend fun getCurrentWeather(
////        @Query("key") appId: String = API_KEY,
////        @Query("q") city: String
////
//    ): List<WeatherDTO>

    @GET("data/2.5/weather?lang=ru&q=Москва&appid=74c6e18ef7e46d0437362b309d2a60df")
    suspend fun getCurrentWeather(
//        @Query("q") city: String,
//        @Query("appid") appId: String = API_KEY
//
    ): List<WeatherDTO>
}