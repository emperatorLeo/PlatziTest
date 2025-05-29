package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlatziViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<List<SoundDto>>(emptyList())
    val uiState: StateFlow<List<SoundDto>> = _uiState.asStateFlow()

    fun searchSound(query: String) {
        viewModelScope.launch {
            searchUseCase(query).collect {
                if (it.isSuccessful) {
                    _uiState.value = it.body() ?: emptyList()
                }
            }
        }
    }
}
