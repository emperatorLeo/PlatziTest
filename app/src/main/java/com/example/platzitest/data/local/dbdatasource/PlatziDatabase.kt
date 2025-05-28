package com.example.platzitest.data.local.dbdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.platzitest.data.local.dao.PlatziDao
import com.example.platzitest.data.model.entities.SoundEntity

@Database(entities = [SoundEntity::class], version = 1, exportSchema = false)
abstract class PlatziDatabase : RoomDatabase() {
    abstract fun platziDao(): PlatziDao
}