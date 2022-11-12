package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO (
    @SerializedName("id")
    val id: Int,
    @SerializedName("id")
    val name: String,
    @SerializedName("cod")
    val cod: Int
) : Parcelable