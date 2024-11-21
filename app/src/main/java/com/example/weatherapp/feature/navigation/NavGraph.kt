package com.example.weatherapp.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.weatherapp.feature.search.presentation.SearchCityViewModel
import com.example.weatherapp.feature.search.presentation.SearchScreen
import com.example.weatherapp.feature.weather.presentation.WeatherScreen
import com.example.weatherapp.feature.weather.presentation.WeatherViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Search

@Serializable
data class Weather(val lat: Double, val lon: Double)

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Search> {
            val viewModel: SearchCityViewModel = hiltViewModel()
            val cityStateList by viewModel.cityList.collectAsStateWithLifecycle()
            val isSearchInitiated by viewModel.isSearchInitiated

            SearchScreen(
                modifier = modifier,
                uiStates = cityStateList,
                cityName = viewModel.searchCityState.value,
                onTextChange = viewModel::onSearchTextChange,
                onSearch = viewModel::onSearchStart,
                displaySearchResult = isSearchInitiated,
                onCitySelect = {
                    navController.navigate(Weather(it.lat, it.lon))
                }
            )
        }

        composable<Weather> {
            val weather = it.toRoute<Weather>()
            val viewModel: WeatherViewModel =
                hiltViewModel(
                    creationCallback = { factory: WeatherViewModel.WeatherViewModelFactory ->
                        factory.create(weather.lat, weather.lon)
                    }
                )

            val uiState by viewModel.weatherState.collectAsStateWithLifecycle()
            WeatherScreen(
                modifier = modifier,
                uiState = uiState
            )
        }
    }
}