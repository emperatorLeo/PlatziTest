package com.example.platzitest.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

import com.example.platzitest.domain.dtos.SoundDto

@Composable
fun DetailScreen(soundItem: State<SoundDto?>) {
    if (soundItem.value != null) {
        Log.d("Leo","sound selected: ${soundItem.value}")
    }
}