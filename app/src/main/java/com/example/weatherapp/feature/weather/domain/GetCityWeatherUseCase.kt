package com.example.weatherapp.feature.weather.domain

import com.example.weatherapp.core.UiState
import com.example.weatherapp.core.UiState.Success
import com.example.weatherapp.di.IoDispatcher
import com.example.weatherapp.feature.weather.domain.mapper.toCityWeatherState
import com.example.weatherapp.feature.weather.presentation.CityWeatherState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(lat: Double, lon: Double): UiState<CityWeatherState> =
        withContext(ioDispatcher) {
            val response = repository.getCityWeather(lat, lon)
            return@withContext if (response.isSuccessful) {
                response.body()?.let { Success(it.toCityWeatherState()) }
                    ?: UiState.Error("No data found, please try again later") //Todo use string resource
            } else {
                UiState.Error("Something went wrong") //Todo use string resource
            }
        }
}