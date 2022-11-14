package com.example.avitotechweather.utils

import java.sql.Date
import java.text.SimpleDateFormat

object Utils {
    const val BASE_URL = "http://api.openweathermap.org/"
    const val API_KEY = "74c6e18ef7e46d0437362b309d2a60df"

    fun getIcon(str: String): String = "http://openweathermap.org/img/w/${str}.png"
    fun getProperDateTime(date: Long): String {
        val dtFormat =
            SimpleDateFormat("Дата: dd-MM-YYYY \nВремя взятия метеоданных по вашему ч.п.: HH:mm")
        dtFormat.timeZone
        return dtFormat.format(Date(date * 1000))
    }
}