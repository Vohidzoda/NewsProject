package com.example.domain.model.usecase.settings

import com.example.domain.model.repository.CategoryPreferenceRepository
import javax.inject.Inject

class SetCategoryEnabledUseCase @Inject constructor(
    private val repository: CategoryPreferenceRepository
) {
    suspend operator fun invoke(category: String, isEnabled: Boolean) {
        repository.setCategoryEnabled(category, isEnabled)
    }
}
