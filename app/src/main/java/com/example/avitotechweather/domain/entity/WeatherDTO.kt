package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO (
    @SerializedName("name")
    val temperature: String,
    @SerializedName("localtime")
    val dateTime: String
) : Parcelable