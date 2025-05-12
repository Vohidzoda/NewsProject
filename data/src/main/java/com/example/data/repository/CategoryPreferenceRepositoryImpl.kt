package com.example.data.repository

import android.content.SharedPreferences
import com.example.domain.model.model.SelectedCategory
import com.example.domain.model.repository.CategoryPreferenceRepository
import jakarta.inject.Inject

class CategoryPreferenceRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
) : CategoryPreferenceRepository {

    override suspend fun getSelectedCategories(): List<SelectedCategory> {
        return listOf("politics", "sport", "business", "science").map { category ->
            val isEnabled = prefs.getBoolean(category, true)
            SelectedCategory(category, isEnabled)
        }
    }

    override suspend fun setCategoryEnabled(category: String, enabled: Boolean) {
        prefs.edit().putBoolean(category, enabled).apply()
    }
}