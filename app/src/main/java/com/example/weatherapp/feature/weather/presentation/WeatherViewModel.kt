package com.example.weatherapp.feature.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.UiState
import com.example.weatherapp.feature.weather.domain.GetCityWeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = WeatherViewModel.WeatherViewModelFactory::class)
class WeatherViewModel @AssistedInject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    @Assisted("lat") private val lat: Double,
    @Assisted("lon") private val lon: Double
) : ViewModel() {

    private val _weatherSate: MutableStateFlow<UiState<CityWeatherState>> =
        MutableStateFlow(UiState.Loading)
    val weatherState: StateFlow<UiState<CityWeatherState>> = _weatherSate.onStart {
        fetchCityWeatherState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    private fun fetchCityWeatherState() {
        viewModelScope.launch {
            _weatherSate.emit(getCityWeatherUseCase(lat, lon))
        }
    }


    @AssistedFactory
    interface WeatherViewModelFactory {
        fun create(@Assisted("lat") lat: Double, @Assisted("lon") lon: Double): WeatherViewModel
    }
}