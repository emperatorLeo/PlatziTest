package com.example.platzitest.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.platzitest.common.SOUND_TABLE
import com.example.platzitest.domain.dtos.SoundDto

@Entity(tableName = SOUND_TABLE)
data class SoundEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val like: Boolean = false
) {
    fun fromEntityToDto(): SoundDto {
        this.apply {
            return SoundDto(
                id = this.id,
                name = this.name,
                username = this.userName,
                like = this.like
            )
        }
    }
}