package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherWeatherListDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
