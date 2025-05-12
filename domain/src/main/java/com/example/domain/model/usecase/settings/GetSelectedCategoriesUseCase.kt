package com.example.domain.model.usecase.settings

import com.example.domain.model.model.SelectedCategory
import com.example.domain.model.repository.CategoryPreferenceRepository
import javax.inject.Inject

class GetSelectedCategoriesUseCase @Inject constructor(
    private val repository: CategoryPreferenceRepository
) {
    suspend operator fun invoke(): List<SelectedCategory> {
        return repository.getSelectedCategories()
    }
}
