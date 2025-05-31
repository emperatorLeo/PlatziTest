package com.example.details.domain.usecase

import com.example.datasource.data.repository.Repository
import javax.inject.Inject

class GetSoundInfoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(soundId: Int) = repository.getSoundInfo(soundId)
}