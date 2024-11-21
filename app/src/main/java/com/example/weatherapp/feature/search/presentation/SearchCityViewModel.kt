package com.example.weatherapp.feature.search.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.UiState
import com.example.weatherapp.feature.search.domain.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private val _cityList: MutableStateFlow<UiState<ImmutableList<CityState>>> =
        MutableStateFlow(UiState.Loading)
    val cityList: StateFlow<UiState<ImmutableList<CityState>>> = _cityList.asStateFlow()

    var searchCityState: MutableState<String> = mutableStateOf("")
        private set

    var isSearchInitiated: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun onSearchTextChange(searchText: String) {
        searchCityState.value = searchText
        setIsSearchInitiated()
    }

    fun onSearchStart() {
        viewModelScope.launch {
            setIsSearchInitiated(true)
            _cityList.emit(UiState.Loading)
            val data = searchCityUseCase(searchCityState.value)
            _cityList.emit(data)
        }
    }

    private fun setIsSearchInitiated(isInitiated: Boolean = false) {
        isSearchInitiated.value = isInitiated
    }
}