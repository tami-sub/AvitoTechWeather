package com.example.avitotechweather.domain.entity


import com.google.gson.annotations.SerializedName

data class WeatherAllDTO(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<Day>,
    @SerializedName("message")
    val message: Int
)