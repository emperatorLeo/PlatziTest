package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.domain.dtos.SoundDto
import com.example.platzitest.domain.usecase.DeleteUseCase
import com.example.platzitest.domain.usecase.InsertUseCase
import com.example.platzitest.domain.usecase.ReadUseCase
import com.example.platzitest.domain.usecase.SearchUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import com.example.details.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readUseCase: ReadUseCase,
    private val searchUseCase: SearchUseCase,
    private val updateUseCase: UpdateUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val insertUseCase: InsertUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun readFromDatabase() {
        viewModelScope.launch {
            readUseCase().collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun searchSound(query: String) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            searchUseCase(query).collect {
                if (it.isSuccessful) {
                    if (it.body().isNullOrEmpty()){
                        _uiState.value = UiState.EmptyList
                    } else {
                        _uiState.value = UiState.Success(it.body() ?: emptyList())
                    }
                }
            }
        }
    }

    fun updateSound(soundDto: SoundDto) {
        viewModelScope.launch {
            updateUseCase(soundDto)
        }
    }

    fun deleteSound(soundDto: SoundDto) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            deleteUseCase(soundDto).collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun insertSound() {
        _uiState.value = UiState.Loading
        val newSound =
            SoundDto(id = Random.nextInt(1000), name = "New Sound", username = "New user", like = false)
        val newList = mutableListOf(newSound)
        viewModelScope.launch {
            insertUseCase(newSound).collect {
                _uiState.value = UiState.Success(newList)
            }
        }
    }
}
