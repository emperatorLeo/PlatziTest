package com.example.datasource.data.local.services

import com.example.datasource.data.model.entities.SoundEntity
import kotlinx.coroutines.flow.Flow

interface LocalServices {

    suspend fun feedDatabase(soundList: List<SoundEntity>)

    suspend fun insertSound(sound: SoundEntity)

    suspend fun updateSound(sound: SoundEntity)

    suspend fun getSounds(): Flow<List<SoundEntity>>

    suspend fun getSoundById(id:Int): Flow<SoundEntity>

    suspend fun deleteSound(sound: SoundEntity)

    suspend fun deleteAll()

}