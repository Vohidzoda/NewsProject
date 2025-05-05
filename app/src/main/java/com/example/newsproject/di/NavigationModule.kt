package com.example.newsproject.di

import com.example.newsproject.presentation.navigation.BottomNavHandler
import com.example.newsproject.presentation.navigation.BottomNavHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {
    @Provides
    fun provideBottomNavHandler(): BottomNavHandler = BottomNavHandlerImpl()
}