package com.example.datasource.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.datasource.common.SOUND_TABLE
import com.example.datasource.domain.dtos.SoundDto

@Entity(tableName = SOUND_TABLE)
data class SoundEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val isFavorite: Boolean = false
) {
    fun fromEntityToDto(): SoundDto {
        this.apply {
            return SoundDto(
                id = this.id,
                name = this.name,
                username = this.userName,
                like = this.isFavorite
            )
        }
    }
}