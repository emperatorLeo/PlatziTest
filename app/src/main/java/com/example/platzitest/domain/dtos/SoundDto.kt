package com.example.platzitest.domain.dtos

import com.example.platzitest.data.model.entities.SoundEntity

data class SoundDto(val id: Int, val name: String, val username: String, var like: Boolean) {
    fun fromDtoToEntity(): SoundEntity {
        this.apply {
            return SoundEntity(
                id = this.id,
                name = this.name,
                userName = this.username,
                isFavorite = this.like
            )
        }
    }
}