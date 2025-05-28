package com.example.platzitest.data.repository

import android.util.Log
import com.example.platzitest.data.local.services.LocalServices
import com.example.platzitest.data.model.entities.SoundEntity
import com.example.platzitest.data.remote.apidatasource.ApiDataSource
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Response.error

class RepositoryImp(
    private val apiDataSource: ApiDataSource,
    private val localServices: LocalServices
) : Repository {

    override suspend fun getSearch(query: String) = flow<Response<List<SoundEntity>>> {
        apiDataSource.getSearch(query).collect {
            if (it.isSuccessful) {
                val listEntity =
                    it.body()?.results?.map { soundItem -> soundItem.fromResponseToEntity() }

                localServices.insertSound(listEntity ?: emptyList())

                localServices.getSounds().collect { list ->
                    emit(Response.success(list))
                }
            } else {
                emit(error(it.code(), it.errorBody()!!))
            }
        }
    }
}
