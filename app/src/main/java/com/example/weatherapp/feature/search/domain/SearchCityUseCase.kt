package com.example.weatherapp.feature.search.domain

import com.example.weatherapp.core.UiState
import com.example.weatherapp.core.UiState.Success
import com.example.weatherapp.di.IoDispatcher
import com.example.weatherapp.feature.search.domain.mapper.toCityState
import com.example.weatherapp.feature.search.presentation.CityState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository: SearchRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(city: String): UiState<ImmutableList<CityState>> =
        withContext(ioDispatcher) {
            val response = repository.searchCity(city)
            return@withContext if (response.isSuccessful) {
                Success(
                    response.body()
                        ?.filter { it.country == "US" }
                        ?.map {
                            it.toCityState()
                        }?.toPersistentList() ?: persistentListOf()
                )
            } else {
                UiState.Error("Something went wrong") //Todo use from string resource
            }
        }
}