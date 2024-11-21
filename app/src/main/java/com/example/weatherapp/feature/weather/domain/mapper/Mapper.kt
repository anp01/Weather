package com.example.weatherapp.feature.weather.domain.mapper

import com.example.weatherapp.feature.weather.data.dto.WeatherDto
import com.example.weatherapp.feature.weather.presentation.CityWeatherState

fun WeatherDto.toCityWeatherState(): CityWeatherState {
    return CityWeatherState(
        name = name.orEmpty(),
        temperature = main?.temp.kelvinToFahrenheit().toString(),
        minTemperature = main?.tempMin.kelvinToFahrenheit().toString(),
        maxTemperature = main?.tempMax.kelvinToFahrenheit().toString(),
        feelsLikeTemperature = main?.feelsLike.kelvinToFahrenheit().toString(),
        icon = weather?.firstOrNull()?.icon.orEmpty()
    )
}

fun Double?.kelvinToFahrenheit(): Int {
    return if (this != null) ((this - 273.15) * 9 / 5 + 32).toInt() else 0
}