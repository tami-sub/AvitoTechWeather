package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherWindListDTO(
    val speed: Float,
    val deg: Int
) : Parcelable
