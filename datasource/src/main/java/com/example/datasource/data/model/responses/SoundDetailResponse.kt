package com.example.datasource.data.model.responses

import com.example.datasource.domain.dtos.SoundDetailsDto
import com.google.gson.annotations.SerializedName

data class SoundDetailResponse(
    @SerializedName("id") val soundId: Int,
    @SerializedName("name") val soundName: String,
    @SerializedName("description") val description: String,
    @SerializedName("previews") val previews: Preview,
    @SerializedName("images") val images: SoundImages
){
    fun fromResponseToDto(): SoundDetailsDto {
        return SoundDetailsDto(
            id = soundId,
            name = soundName,
            description = description,
            previewMp3 = previews.previewMp3,
            previewOgg = previews.previewOgg,
            imageUrl = images.waveform
        )
    }
}

data class Preview(
    @SerializedName("preview-hq-mp3") val previewMp3: String,
    @SerializedName("preview-hq-ogg") val previewOgg: String
)

data class SoundImages(@SerializedName("waveform_m") val waveform: String)