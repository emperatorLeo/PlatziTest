package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.domain.usecase.GetSoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getSoundUseCase: GetSoundUseCase) :
    ViewModel() {
    private val _sound = MutableStateFlow<SoundDto?>(null)
    val sound: MutableStateFlow<SoundDto?> = _sound

    fun getSoundById(soundId: Int) {
        viewModelScope.launch {
            getSoundUseCase(soundId).collect {
                 _sound.value = it
            }
        }
    }
}