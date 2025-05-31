package com.example.datasource.data.remote.services

import com.example.datasource.data.model.responses.SoundDetailResponse
import com.example.datasource.data.model.responses.SoundResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SoundService {

    @GET("search/text")
    suspend fun getSearchSound(
        @Query("query") query: String,
        @Query("token") apiKey: String,
    ): Response<SoundResponse>

    @GET("sounds/{soundId}")
    suspend fun getSoundInfo(
        @Path("soundId") soundId: Int,
        @Query("token") apiKey: String,
    ): Response<SoundDetailResponse>
}