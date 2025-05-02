package com.example.newsproject.di

import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.toolbar.ToolbarManager
import com.example.newsproject.presentation.toolbar.menu.MenuItemAction
import com.example.newsproject.presentation.toolbar.menu.NotificationMenuItemAction
import com.example.newsproject.presentation.toolbar.menu.SearchMenuItemAction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToolbarModule {

    @Provides
    @Singleton
    fun provideToolbarHandler(
        manager: ToolbarManager
    ): ToolbarHandler = manager

    @Provides
    fun provideMenuItemActions(
        searchAction: SearchMenuItemAction,
        notificationAction: NotificationMenuItemAction
    ): Set<MenuItemAction> = setOf(searchAction, notificationAction)
}
