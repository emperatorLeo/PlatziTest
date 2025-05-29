package com.example.platzitest.domain.usecase

import com.example.platzitest.data.repository.Repository
import com.example.platzitest.domain.dtos.SoundDto
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(soundDto: SoundDto) = repository.updateSound(soundDto)
}