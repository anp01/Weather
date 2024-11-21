package com.example.weatherapp.feature.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.core.UiState
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchScreen(
    cityName: String,
    uiStates: UiState<ImmutableList<CityState>>,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onCitySelect: (CityState) -> Unit,
    modifier: Modifier = Modifier,
    displaySearchResult: Boolean = false
) {

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            stringResource(R.string.search_city),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = cityName,
            onValueChange = {
                onTextChange(it)
            },
            label = {
                Text(text = stringResource(R.string.enter_city_name))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })
        )

        AnimatedVisibility(displaySearchResult) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (uiStates) {
                    is UiState.Error -> Text(text = uiStates.error)
                    UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    is UiState.Success -> {
                        val cityList = uiStates.data
                        if (cityList.isNotEmpty()) {
                            LazyColumn(modifier = Modifier) {
                                item {
                                    Text(
                                        stringResource(R.string.results),
                                        modifier = Modifier.padding(top = 16.dp)
                                    )
                                }
                                items(cityList) { city ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { onCitySelect(city) }
                                            .padding(top = 8.dp)
                                    ) {
                                        Text(
                                            text = city.name,
                                            style = MaterialTheme.typography.headlineMedium
                                        )
                                        Text(
                                            text = stringResource(R.string.state, city.state),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        HorizontalDivider()
                                    }
                                }
                            }
                        } else {
                            Text(
                                stringResource(R.string.no_data_found_please_try_again),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}