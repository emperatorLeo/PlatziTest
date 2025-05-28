package com.example.platzitest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.platzitest.data.model.entities.SoundEntity

@Dao
interface PlatziDao {
    @Insert
    suspend fun insertSound(soundList: List<SoundEntity>)
}