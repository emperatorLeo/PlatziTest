package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.usecase.GetSoundInfoUseCase
import com.example.platzitest.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSoundInfoUseCase: GetSoundInfoUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val sound: MutableStateFlow<UiState> = _uiState

    fun getSoundById(soundId: Int) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            getSoundInfoUseCase(soundId).collect {
                if (it.isSuccessful){
                    _uiState.value = UiState.Success(it.body())
                }
            }
        }
    }
}