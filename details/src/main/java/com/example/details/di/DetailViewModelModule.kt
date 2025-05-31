package com.example.details.di

import com.example.datasource.di.ApiModule
import com.example.details.domain.usecase.GetSoundInfoUseCase
import com.example.details.presentation.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module(includes = [ApiModule::class])
@InstallIn(ViewModelComponent::class)
object DetailViewModelModule {

    @Provides
    fun provideDetailViewModel(
        getSoundInfoUseCase: GetSoundInfoUseCase,
    ): DetailViewModel {
        return DetailViewModel(getSoundInfoUseCase)
    }
}