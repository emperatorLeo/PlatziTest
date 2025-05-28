package com.example.platzitest.data.remote.apidatasource

import com.example.platzitest.data.model.responses.SoundResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiDataSource {
    suspend fun getSearch(query: String): Flow<Response<SoundResponse>>
}