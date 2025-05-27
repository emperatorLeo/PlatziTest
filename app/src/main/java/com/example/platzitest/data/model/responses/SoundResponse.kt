package com.example.platzitest.data.model.responses

import com.google.gson.annotations.SerializedName

data class SoundResponse(@SerializedName("results") val results: List<SoundItem>)
data class SoundItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
)