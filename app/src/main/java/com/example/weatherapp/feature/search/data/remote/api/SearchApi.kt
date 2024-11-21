package com.example.weatherapp.feature.search.data.remote.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.feature.search.data.remote.dto.SearchCityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") city: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") appid: String = BuildConfig.API_KEY,
    ): Response<SearchCityDto>
}