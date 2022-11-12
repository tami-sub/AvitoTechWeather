package com.example.avitotechweather.data.network

import com.example.avitotechweather.data.network.Utils.API_KEY
import com.example.avitotechweather.domain.entity.CityDTO
import com.example.avitotechweather.domain.entity.SuccessDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("one-to-one-query/api/121/v2/{oneToOneId}")
    suspend fun getCityLatLon(
        @Query("q") city: String,
        @Query("appid") appId: String = API_KEY
    ): Call<CityDTO>

//    @GET("data/2.5/weather")
//    suspend fun getWeatherWeek(
//        @Query("lat") lat: String,
//        @Query("lon") lon: String,
//        @Query("appid") appId: String
//
////            @Query("key")
////            key:String = API_KEY,
////            @Query("q")
////            q:String = CITY,
////            @Query("days")
////            days: String = DAYS,
////            @Query("aqi")
////            aqi: String = QUALITY_AIR,
////            @Query("alerts")
////            alerts:String = ALERTS,
////            @Query("lang")
////            lang:String = "ru"
//
//    ): Result<SuccessDTO>
}