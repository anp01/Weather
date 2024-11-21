package com.example.weatherapp.core

sealed class UiState<out T>(
) {
    data object Loading : UiState<Nothing>()
    data class Error(val error: String) : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
}