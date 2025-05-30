package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.domain.usecase.GetSoundUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSoundUseCase: GetSoundUseCase,
    private val updateUseCase: UpdateUseCase
) :
    ViewModel() {
    private val _sound = MutableStateFlow(SoundDto.EMPTY())
    val sound: MutableStateFlow<SoundDto> = _sound

    fun getSoundById(soundId: Int) {
        viewModelScope.launch {
            getSoundUseCase(soundId).collect {
                _sound.value = it
            }
        }
    }

    fun updateSound(soundDto: SoundDto) {
        viewModelScope.launch {
            updateUseCase(soundDto)
        }
    }
}