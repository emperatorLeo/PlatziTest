package com.example.platzitest.di

import com.example.datasource.di.ApiModule
import com.example.platzitest.domain.usecase.DeleteUseCase
import com.example.platzitest.domain.usecase.InsertUseCase
import com.example.platzitest.domain.usecase.ReadUseCase
import com.example.platzitest.domain.usecase.SearchUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import com.example.platzitest.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module(includes = [ApiModule::class])
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModel(
        readUseCase: ReadUseCase,
        searchUseCase: SearchUseCase,
        updateUseCase: UpdateUseCase,
        deleteUseCase: DeleteUseCase,
        insertUseCase: InsertUseCase
    ): MainViewModel {
        return MainViewModel(
            readUseCase,
            searchUseCase,
            updateUseCase,
            deleteUseCase,
            insertUseCase
        )
    }
}