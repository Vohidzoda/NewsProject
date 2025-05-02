package com.example.newsproject.di

import android.content.Context
import com.example.newsproject.presentation.handler.UrlHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrlHandlerModule {
    @Provides
    @Singleton
    fun provideUrlHandler(@ApplicationContext context: Context): UrlHandler {
        return UrlHandler(context)
    }
}
