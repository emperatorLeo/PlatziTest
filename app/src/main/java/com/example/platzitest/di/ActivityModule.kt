package com.example.platzitest.di

import android.app.Activity
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideExoPlayer(activity: Activity): ExoPlayer {
        return ExoPlayer.Builder(activity).build()
    }

}