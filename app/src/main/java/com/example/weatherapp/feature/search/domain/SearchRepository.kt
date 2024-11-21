package com.example.weatherapp.feature.search.domain

import com.example.weatherapp.feature.search.data.remote.dto.SearchCityDto
import retrofit2.Response

interface SearchRepository {

    suspend fun searchCity(city: String): Response<SearchCityDto>
}