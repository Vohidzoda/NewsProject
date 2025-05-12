package com.example.newsproject.di

import android.content.Context
import com.example.newsproject.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //TODO: это не нужно!
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}