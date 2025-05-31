package com.example.datasource.data.local.dbdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.data.local.dao.PlatziDao
import com.example.datasource.data.model.entities.SoundEntity

@Database(entities = [SoundEntity::class], version = 1, exportSchema = false)
abstract class PlatziDatabase : RoomDatabase() {
    abstract fun platziDao(): PlatziDao
}