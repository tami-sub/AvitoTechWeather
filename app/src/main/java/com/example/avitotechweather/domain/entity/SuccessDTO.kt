package com.example.avitotechweather.domain.entity

import com.google.gson.annotations.SerializedName

data class SuccessDTO(
    @SerializedName("message")
    val message: String
)
