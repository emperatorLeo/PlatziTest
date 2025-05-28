package com.example.platzitest.data.remote.services

import com.example.platzitest.data.model.responses.SoundResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SoundService {

    @GET("search/text")
    suspend fun getSearchSound(
        @Query("query") query: String,
        @Query("token") apiKey: String,
    ): Response<SoundResponse>
}