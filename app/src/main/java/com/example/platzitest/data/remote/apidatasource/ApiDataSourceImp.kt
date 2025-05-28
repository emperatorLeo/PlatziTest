package com.example.platzitest.data.remote.apidatasource

import com.example.platzitest.BuildConfig.API_KEY
import com.example.platzitest.data.model.responses.SoundResponse
import com.example.platzitest.data.remote.services.SoundService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ApiDataSourceImp(private val soundService: SoundService) : ApiDataSource {

    override suspend fun getSearch(query:String): Flow<Response<SoundResponse>> = flow {
        emit(soundService.getSearchSound(query, API_KEY))
    }
}