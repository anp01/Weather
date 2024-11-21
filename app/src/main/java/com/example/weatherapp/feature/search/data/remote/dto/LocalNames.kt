package com.example.weatherapp.feature.search.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LocalNames(
    @SerializedName("en")
    val en: String?,
    @SerializedName("ta")
    val ta: String?
)