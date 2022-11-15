package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherCurrentDTO (
    @SerializedName("name")
    val name: String,
    @SerializedName("dt")
    val dataTime: Long,
    @SerializedName("timezone")
    val timezone: Long,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) : Parcelable