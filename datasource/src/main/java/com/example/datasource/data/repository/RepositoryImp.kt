package com.example.datasource.data.repository

import com.example.datasource.data.local.services.LocalServices
import com.example.datasource.data.remote.apidatasource.ApiDataSource
import com.example.datasource.domain.dtos.SoundDetailsDto
import com.example.datasource.domain.dtos.SoundDto
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Response.error
import retrofit2.Response.success

class RepositoryImp(
    private val apiDataSource: ApiDataSource,
    private val localServices: LocalServices
) : Repository {

    override suspend fun getSearch(query: String) = flow<Response<List<SoundDto>>> {
        apiDataSource.getSearch(query).collect {
            if (it.isSuccessful) {
                if (it.body()?.results.isNullOrEmpty()) {
                    emit(success(emptyList()))
                } else {
                    localServices.deleteAll()
                    val listEntity =
                        it.body()?.results?.map { soundItem -> soundItem.fromResponseToEntity() }

                    localServices.feedDatabase(listEntity ?: emptyList())
                    getFromDataBase().collect { list ->
                        emit(success(list))
                    }
                }
            } else {
                emit(error(it.code(), it.errorBody()!!))
            }
        }
    }

    override suspend fun getSoundInfo(id: Int) = flow<Response<SoundDetailsDto>> {
        apiDataSource.getSoundInfo(id).collect {
            if (it.isSuccessful) {
                val soundEntity = it.body()?.fromResponseToDto()
                emit(success(soundEntity))
            } else {
                emit(error(it.code(), it.errorBody()!!))
            }
        }
    }

    override suspend fun insertSound(sound: SoundDto) = flow {
        localServices.insertSound(sound.fromDtoToEntity())
        getFromDataBase().collect {
            emit(it)
        }
    }

    override suspend fun updateSound(sound: SoundDto) {
        localServices.updateSound(sound.fromDtoToEntity())
    }

    override suspend fun getFromDataBase() = flow {
        localServices.getSounds().collect { list ->
            val listDto = list.map { soundEntity -> soundEntity.fromEntityToDto() }
            emit(listDto)
        }
    }

    override suspend fun getSoundById(id: Int) = flow {
        localServices.getSoundById(id).collect {
            emit(it.fromEntityToDto())
        }
    }

    override suspend fun deleteSound(sound: SoundDto) = flow {
        localServices.deleteSound(sound.fromDtoToEntity())
        getFromDataBase().collect {
            emit(it)
        }
    }
}
