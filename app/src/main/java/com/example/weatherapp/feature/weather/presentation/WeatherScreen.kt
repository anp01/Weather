package com.example.weatherapp.feature.weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.core.UiState

@Composable
fun WeatherScreen(
    uiState: UiState<CityWeatherState>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Error -> Text(text = uiState.error)
            UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.Success -> {
                val weather = uiState.data
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(weather.name, style = MaterialTheme.typography.headlineLarge)
                    Text(
                        weather.temperature,
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 80.sp)
                    )
                    Text(
                        stringResource(R.string.feels_like, weather.feelsLikeTemperature),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        stringResource(
                            R.string.max_min,
                            weather.maxTemperature,
                            weather.minTemperature
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    AsyncImage(
                        model = stringResource(R.string.weather_icon_url, weather.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }

}