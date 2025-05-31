package com.example.datasource.data.remote.apidatasource

import com.example.datasource.BuildConfig.API_KEY
import com.example.datasource.data.model.responses.SoundDetailResponse
import com.example.datasource.data.model.responses.SoundResponse
import com.example.datasource.data.remote.services.SoundService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ApiDataSourceImp(private val soundService: SoundService) : ApiDataSource {

    override suspend fun getSearch(query: String): Flow<Response<SoundResponse>> = flow {
        emit(soundService.getSearchSound(query, API_KEY))
    }

    override suspend fun getSoundInfo(soundId: Int): Flow<Response<SoundDetailResponse>> = flow {
        emit(soundService.getSoundInfo(soundId, API_KEY))
    }
}