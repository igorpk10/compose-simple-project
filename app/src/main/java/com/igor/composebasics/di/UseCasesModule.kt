package com.igor.composebasics.di

import com.igor.composebasics.domain.usecases.GetPicturesUseCase
import com.igor.composebasics.domain.usecases.GetPicturesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {
    @Binds
    fun bindGetPicturesUseCase(useCase: GetPicturesUseCaseImpl): GetPicturesUseCase
}