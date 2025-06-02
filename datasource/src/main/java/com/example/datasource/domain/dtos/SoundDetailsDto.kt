package com.example.datasource.domain.dtos

data class SoundDetailsDto(
    val id: Int,
    val name: String,
    val description: String,
    val previewMp3: String,
    val previewOgg: String,
    val imageUrl: String
)