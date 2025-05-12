package com.example.newsproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.model.SelectedCategory
import com.example.domain.model.usecase.settings.GetSelectedCategoriesUseCase
import com.example.domain.model.usecase.settings.SetCategoryEnabledUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetSelectedCategoriesUseCase,
    private val setCategoryUseCase: SetCategoryEnabledUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<SelectedCategory>>(emptyList())
    val categories: StateFlow<List<SelectedCategory>> = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categories.value = getCategoriesUseCase()
        }
    }

    fun onCategoryChecked(category: String, isChecked: Boolean) {
        viewModelScope.launch {
            setCategoryUseCase(category, isChecked)
            loadCategories()
        }
    }
}
