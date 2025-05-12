package com.example.newsproject.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.repository.CategoryPreferenceRepositoryImpl
import com.example.domain.model.repository.CategoryPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryPreferenceModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("category_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideCategoryPreferenceRepository(
        prefs: SharedPreferences
    ): CategoryPreferenceRepository = CategoryPreferenceRepositoryImpl(prefs)
}
