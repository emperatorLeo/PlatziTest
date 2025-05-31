package com.example.datasource.di

import android.content.Context
import androidx.room.Room
import com.example.datasource.common.DATA_BASE_NAME
import com.example.datasource.data.local.dbdatasource.PlatziDatabase
import com.example.datasource.data.local.services.LocalServices
import com.example.datasource.data.local.services.LocalServicesImp
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

    @Singleton
    @Provides
    fun provideLocalServices(db: PlatziDatabase): LocalServices {
        return LocalServicesImp(db)
    }

}