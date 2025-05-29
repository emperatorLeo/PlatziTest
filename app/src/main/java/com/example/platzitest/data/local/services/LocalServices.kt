package com.example.platzitest.data.local.services

import com.example.platzitest.data.model.entities.SoundEntity
import kotlinx.coroutines.flow.Flow

interface LocalServices {

    suspend fun insertSound(soundList: List<SoundEntity>)

    suspend fun updateSound(sound: SoundEntity)

    suspend fun getSounds(): Flow<List<SoundEntity>>

}