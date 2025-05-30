package com.example.platzitest.domain.usecase

import com.example.platzitest.data.repository.Repository
import javax.inject.Inject

class GetSoundInfoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(soundId: Int) = repository.getSoundInfo(soundId)
}