package com.example.newsproject.di

import android.content.Context
import com.example.newsproject.presentation.swipe.handler.HistorySwipeHandler
import com.example.newsproject.presentation.swipe.handler.FavoriteSwipeHandler
import com.example.newsproject.presentation.swipe.factory.SwipeHandlerFactory
import com.example.newsproject.presentation.swipe.factory.SwipeHandlerFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
object SwipeModule {

    @Provides
    fun provideHistorySwipeHandler(
        @ActivityContext context: Context
    ): HistorySwipeHandler = HistorySwipeHandler(context)

    @Provides
    fun provideFavoriteSwipeHandler(
        @ActivityContext context: Context
    ): FavoriteSwipeHandler = FavoriteSwipeHandler(context)

    @Provides
    fun provideSwipeHandlerFactory(
        historySwipeHandler: HistorySwipeHandler,
        favoriteSwipeHandler: FavoriteSwipeHandler
    ): SwipeHandlerFactory = SwipeHandlerFactoryImpl(historySwipeHandler, favoriteSwipeHandler)
}
