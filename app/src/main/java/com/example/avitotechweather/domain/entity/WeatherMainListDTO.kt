package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherMainListDTO(
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    val humidity: Int,
    val pressure: Int
) : Parcelable