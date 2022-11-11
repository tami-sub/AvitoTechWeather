package com.example.avitotechweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityDTO(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String
) : Parcelable