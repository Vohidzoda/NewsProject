package com.example.newsproject.di

import com.example.domain.model.repository.HistoryRepository
import com.example.domain.model.usecase.history.GetHistoryUseCase
import com.example.domain.model.usecase.history.InsertHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideInsertHistoryUseCase(repository: HistoryRepository): InsertHistoryUseCase {
        return InsertHistoryUseCase(repository)
    }

    @Provides
    fun provideGetHistoryUseCase(repository: HistoryRepository): GetHistoryUseCase {
        return GetHistoryUseCase(repository)
    }
}
