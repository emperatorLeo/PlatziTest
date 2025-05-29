package com.example.platzitest.di

import com.example.platzitest.domain.usecase.DeleteUseCase
import com.example.platzitest.domain.usecase.ReadUseCase
import com.example.platzitest.presentation.viewmodel.PlatziViewModel
import com.example.platzitest.domain.usecase.SearchUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun providePlatziViewModel(
        readUseCase: ReadUseCase,
        searchUseCase: SearchUseCase,
        updateUseCase: UpdateUseCase,
        deleteUseCase: DeleteUseCase
    ): PlatziViewModel {
        return PlatziViewModel(readUseCase, searchUseCase, updateUseCase, deleteUseCase)
    }
}