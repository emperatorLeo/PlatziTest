package com.example.platzitest.presentation.state

import com.example.platzitest.domain.dtos.SoundDto

sealed class UiState {
    data class Success(val listSound: List<SoundDto>) : UiState()
    data class Error(val message: String) : UiState()
    data object EmptyList : UiState()
    data object Loading : UiState()
    data object Idle : UiState()
}