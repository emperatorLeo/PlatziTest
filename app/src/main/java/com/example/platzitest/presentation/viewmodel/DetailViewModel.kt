package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.dtos.SoundDetailsDto
import com.example.platzitest.domain.usecase.GetSoundInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSoundInfoUseCase: GetSoundInfoUseCase
) :
    ViewModel() {
    private val _sound = MutableStateFlow(SoundDetailsDto.EMPTY())
    val sound: MutableStateFlow<SoundDetailsDto> = _sound

    fun getSoundById(soundId: Int) {
        viewModelScope.launch {
            getSoundInfoUseCase(soundId).collect {
                if (it.isSuccessful){
                    _sound.value = it.body()!!
                }
            }
        }
    }
}