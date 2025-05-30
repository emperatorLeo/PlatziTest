package com.example.platzitest.presentation.state

sealed class UiState {
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String) : UiState()
    data object EmptyList : UiState()
    data object Loading : UiState()
    data object Idle : UiState()
}