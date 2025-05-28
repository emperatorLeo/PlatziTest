package com.example.platzitest.di

import com.example.platzitest.BuildConfig.BASE_URL
import com.example.platzitest.data.local.services.LocalServices
import com.example.platzitest.data.remote.apidatasource.ApiDataSource
import com.example.platzitest.data.remote.apidatasource.ApiDataSourceImp
import com.example.platzitest.data.remote.services.SoundService
import com.example.platzitest.data.repository.Repository
import com.example.platzitest.data.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [DBModule::class])
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideSoundService(retrofit: Retrofit): SoundService {
        return retrofit.create(SoundService::class.java)
    }

    @Provides
    fun provideApiDataSource(soundService: SoundService): ApiDataSource {
        return ApiDataSourceImp(soundService)
    }

    @Provides
    fun provideRepository(apiDataSource: ApiDataSource, localServices: LocalServices): Repository {
        return RepositoryImp(apiDataSource, localServices)
    }
}