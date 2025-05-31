package com.example.datasource.data.repository

import com.example.datasource.domain.dtos.SoundDetailsDto
import com.example.datasource.domain.dtos.SoundDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getSearch(query: String): Flow<Response<List<SoundDto>>>
    suspend fun getSoundInfo(id: Int): Flow<Response<SoundDetailsDto>>
    suspend fun insertSound(sound: SoundDto): Flow<List<SoundDto>>
    suspend fun updateSound(sound: SoundDto)
    suspend fun getFromDataBase(): Flow<List<SoundDto>>
    suspend fun getSoundById(id: Int): Flow<SoundDto>
    suspend fun deleteSound(sound: SoundDto): Flow<List<SoundDto>>
}
