package com.example.avitotechweather.data.network

import java.sql.Date
import java.text.SimpleDateFormat

object Utils {
    const val BASE_URL = "http://api.openweathermap.org/"
    const val API_KEY = "74c6e18ef7e46d0437362b309d2a60df"

    fun getProperDateTime(date: Long): String {
        val dtFormat = SimpleDateFormat("Дата: dd-MM-YYYY \nВремя: HH:mm")
        dtFormat.timeZone
        return dtFormat.format(Date(date*1000))
    }

//    const val BASE_URL = "https://api.weatherapi.com/"
//    const val API_KEY = "f16f2a05abcd48c985d144600221211"
}