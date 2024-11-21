package com.example.weatherapp.feature.search.domain.mapper

import com.example.weatherapp.feature.search.data.remote.dto.SearchCityDtoItem
import com.example.weatherapp.feature.search.presentation.CityState

internal fun SearchCityDtoItem.toCityState(): CityState {
    return CityState(
        name = name.orEmpty(),
        country = country.orEmpty(),
        lat = lat ?: 0.0,
        lon = lon ?: 0.0,
        state = state.orEmpty()
    )
}