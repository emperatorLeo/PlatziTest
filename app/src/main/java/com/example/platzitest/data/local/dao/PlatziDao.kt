package com.example.platzitest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.platzitest.common.SOUND_TABLE
import com.example.platzitest.data.model.entities.SoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlatziDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSound(soundList: List<SoundEntity>)

    @Query("SELECT * FROM $SOUND_TABLE")
    fun getSounds(): Flow<List<SoundEntity>>

}