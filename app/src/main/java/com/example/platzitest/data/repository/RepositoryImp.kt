package com.example.platzitest.data.repository

import com.example.platzitest.data.local.services.LocalServices
import com.example.platzitest.data.remote.apidatasource.ApiDataSource
import com.example.platzitest.domain.dtos.SoundDto
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Response.error

class RepositoryImp(
    private val apiDataSource: ApiDataSource,
    private val localServices: LocalServices
) : Repository {

    override suspend fun getSearch(query: String) = flow<Response<List<SoundDto>>> {
        apiDataSource.getSearch(query).collect {
            if (it.isSuccessful) {
                val listEntity =
                    it.body()?.results?.map { soundItem -> soundItem.fromResponseToEntity() }

                localServices.insertSound(listEntity ?: emptyList())

                localServices.getSounds().collect { list ->
                    val listDto = list.map { soundEntity -> soundEntity.fromEntityToDto() }
                    emit(Response.success(listDto))
                }
            } else {
                emit(error(it.code(), it.errorBody()!!))
            }
        }
    }
}
