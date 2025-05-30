package com.example.platzitest.domain.dtos

data class SoundDetailsDto(
    val id: Int,
    val name: String,
    val description: String,
    val previewMp3: String,
    val previewOgg: String
){
    companion object {
        fun EMPTY() = SoundDetailsDto(0, "", "", "","")
    }
}