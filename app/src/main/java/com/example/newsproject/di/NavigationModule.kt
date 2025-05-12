package com.example.newsproject.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.newsproject.presentation.navigation.BottomNavHandler
import com.example.newsproject.presentation.navigation.BottomNavHandlerImpl
import com.example.newsproject.presentation.navigator.NewsNavigator
import com.example.newsproject.presentation.navigator.NewsNavigatorImpl
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    fun provideBottomNavHandler(toolbarHandler: ToolbarHandler): BottomNavHandler {
        return BottomNavHandlerImpl(toolbarHandler)
    }

    @Provides
    fun provideNewsNavigator(@ActivityContext context: Context): NewsNavigator {
        return NewsNavigatorImpl(context as FragmentActivity)
    }
}
