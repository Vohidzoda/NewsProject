package com.example.newsproject.di

import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.model.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository
}
