package com.example.weatherapp.feature.weather.data

import com.example.weatherapp.feature.weather.data.api.WeatherApi
import com.example.weatherapp.feature.weather.data.dto.WeatherDto
import com.example.weatherapp.feature.weather.domain.WeatherRepository
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {

    override suspend fun getCityWeather(lat: Double, lon: Double): Response<WeatherDto> {
        return api.getCityWeather(lat, lon)
    }
}