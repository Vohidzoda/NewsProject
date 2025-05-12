package com.example.domain.model.repository

import com.example.domain.model.model.SelectedCategory

interface CategoryPreferenceRepository {
    suspend fun getSelectedCategories(): List<SelectedCategory>
    suspend fun setCategoryEnabled(category: String, enabled: Boolean)
}
