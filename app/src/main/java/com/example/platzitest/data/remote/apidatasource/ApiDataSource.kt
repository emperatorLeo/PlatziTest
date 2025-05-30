package com.example.platzitest.data.remote.apidatasource

import com.example.platzitest.data.model.responses.SoundDetailResponse
import com.example.platzitest.data.model.responses.SoundResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiDataSource {
    suspend fun getSearch(query: String): Flow<Response<SoundResponse>>
    suspend fun getSoundInfo(soundId: Int): Flow<Response<SoundDetailResponse>>
}