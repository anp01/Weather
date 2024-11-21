package com.example.weatherapp.feature.weather.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.feature.weather.data.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCityWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): Response<WeatherDto>
}