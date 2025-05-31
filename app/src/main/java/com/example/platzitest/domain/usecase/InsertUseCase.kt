package com.example.platzitest.domain.usecase

import com.example.platzitest.data.repository.Repository
import com.example.datasource.domain.dtos.SoundDto
import javax.inject.Inject

class InsertUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(sound: SoundDto) = repository.insertSound(sound)
}