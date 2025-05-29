package com.example.platzitest.domain.usecase

import com.example.platzitest.data.repository.Repository
import javax.inject.Inject

class ReadUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getFromDataBase()
}