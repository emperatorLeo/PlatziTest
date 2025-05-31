package com.example.datasource.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.datasource.data.model.entities.SoundEntity
import com.example.datasource.common.SOUND_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlatziDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun feedDataBase(soundList: List<SoundEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSound(sound: SoundEntity)

    @Update
    fun updateSound(sound: SoundEntity)

    @Query("SELECT * FROM $SOUND_TABLE")
    fun getSounds(): Flow<List<SoundEntity>>

    @Query("SELECT * FROM $SOUND_TABLE WHERE id = :id")
    fun getSoundsById(id: Int): Flow<SoundEntity>

    @Delete
    suspend fun deleteSound(sound: SoundEntity)
}
