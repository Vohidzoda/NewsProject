package com.example.newsproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.usecase.settings.GetSelectedCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSelectedCategoriesUseCase: GetSelectedCategoriesUseCase
) : ViewModel() {

    private val _enabledCategories = MutableStateFlow<List<String>>(emptyList())
    val enabledCategories: StateFlow<List<String>> = _enabledCategories

    init {
        loadEnabledCategories()
    }

    fun refreshCategories() {
        loadEnabledCategories()
    }

    private fun loadEnabledCategories() {
        viewModelScope.launch {
            val categories = getSelectedCategoriesUseCase()
                .filter { it.isEnabled }
                .map { it.category }
            _enabledCategories.value = categories
        }
    }
}