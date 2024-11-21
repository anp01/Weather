package com.example.weatherapp.feature.search.data.remote

import com.example.weatherapp.feature.search.data.remote.api.SearchApi
import com.example.weatherapp.feature.search.data.remote.dto.SearchCityDto
import com.example.weatherapp.feature.search.domain.SearchRepository
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: SearchApi) : SearchRepository {

    override suspend fun searchCity(city: String): Response<SearchCityDto> = api.searchCity(city)
}