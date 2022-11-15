package com.example.avitotechweather.domain.entity


import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val h: Double
)