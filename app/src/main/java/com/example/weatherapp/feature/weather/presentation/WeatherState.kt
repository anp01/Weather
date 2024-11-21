package com.example.weatherapp.feature.weather.presentation

data class CityWeatherState(
    val name: String,
    val temperature: String,
    val minTemperature: String,
    val maxTemperature: String,
    val feelsLikeTemperature: String,
    val icon: String
)
