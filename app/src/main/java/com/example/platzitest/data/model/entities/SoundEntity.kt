package com.example.platzitest.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.platzitest.common.SOUND_TABLE

@Entity(tableName = SOUND_TABLE)
data class SoundEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val like: Boolean = false
)