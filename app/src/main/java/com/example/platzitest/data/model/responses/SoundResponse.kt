package com.example.platzitest.data.model.responses

import com.example.platzitest.data.model.entities.SoundEntity
import com.google.gson.annotations.SerializedName

data class SoundResponse(@SerializedName("results") val results: List<SoundItem>)
data class SoundItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
) {
    fun fromResponseToEntity(): SoundEntity {
        this.apply {
            return SoundEntity(
                id = this.id,
                name = this.name,
                userName = this.userName
            )
        }
    }
}