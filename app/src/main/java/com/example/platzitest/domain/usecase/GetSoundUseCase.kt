package com.example.platzitest.domain.usecase

import com.example.datasource.data.repository.Repository
import javax.inject.Inject

class GetSoundUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(soundId: Int) = repository.getSoundById(soundId)
}