package com.example.platzitest.di

import android.content.Context
import androidx.room.Room
import com.example.platzitest.common.DATA_BASE_NAME
import com.example.platzitest.data.local.dbdatasource.PlatziDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): PlatziDatabase {
        return Room.databaseBuilder(appContext, PlatziDatabase::class.java, DATA_BASE_NAME).build()
    }

}