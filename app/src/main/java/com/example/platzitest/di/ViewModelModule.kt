package com.example.platzitest.di

import com.example.platzitest.presentation.viewmodel.PlatziViewModel
import com.example.platzitest.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun providePlatziViewModel(searchUseCase: SearchUseCase): PlatziViewModel {
        return PlatziViewModel(searchUseCase)
    }
}