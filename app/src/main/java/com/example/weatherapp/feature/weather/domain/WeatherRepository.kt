package com.example.weatherapp.feature.weather.domain

import com.example.weatherapp.feature.weather.data.dto.WeatherDto
import retrofit2.Response

interface WeatherRepository {

    suspend fun getCityWeather(lat: Double, lon: Double): Response<WeatherDto>
}