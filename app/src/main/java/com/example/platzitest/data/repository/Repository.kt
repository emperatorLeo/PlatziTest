package com.example.platzitest.data.repository

import com.example.platzitest.data.model.responses.SoundResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getSearch(query: String): Flow<Response<SoundResponse>>
}
