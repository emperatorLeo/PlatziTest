package com.example.platzitest.data.repository

import com.example.platzitest.data.apidatasource.ApiDataSource
import com.example.platzitest.data.model.responses.SoundResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImp(private val apiDataSource: ApiDataSource) : Repository {

    override suspend fun getSearch(query: String): Flow<Response<SoundResponse>> =
        apiDataSource.getSearch(query)
}