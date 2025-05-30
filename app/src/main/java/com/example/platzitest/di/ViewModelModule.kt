package com.example.platzitest.di

import com.example.platzitest.domain.usecase.DeleteUseCase
import com.example.platzitest.domain.usecase.GetSoundInfoUseCase
import com.example.platzitest.domain.usecase.InsertUseCase
import com.example.platzitest.domain.usecase.ReadUseCase
import com.example.platzitest.domain.usecase.SearchUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import com.example.platzitest.presentation.viewmodel.DetailViewModel
import com.example.platzitest.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
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

    @Provides
    fun provideDetailViewModel(
        getSoundInfoUseCase: GetSoundInfoUseCase,
    ): DetailViewModel {
        return DetailViewModel(getSoundInfoUseCase)
    }
}