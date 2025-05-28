package com.example.platzitest.data.repository

import com.example.platzitest.domain.dtos.SoundDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getSearch(query: String): Flow<Response<List<SoundDto>>>
}
