package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO (
    @SerializedName("name")
    val name: String,
    @SerializedName("dt")
    val dataTime: Long,
    @SerializedName("timezone")
    val timezone: Long,
    @SerializedName("main")
    val main: WeatherMainListDTO,
    @SerializedName("weather")
    val weather: List<WeatherWeatherListDTO>,
    @SerializedName("wind")
    val wind: WeatherWindListDTO
) : Parcelable