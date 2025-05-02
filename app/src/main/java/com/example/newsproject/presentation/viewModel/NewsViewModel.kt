package com.example.newsproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewsArticle
import com.example.domain.model.usecase.GetNewsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
): ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsArticle>>(emptyList())
    val newsList: StateFlow<List<NewsArticle>> = _newsList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadNews(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val articles = getNewsByCategoryUseCase(category)
                _newsList.value = articles
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Не удалось загрузить новости"
                _newsList.value = emptyList()
            }finally {
                _isLoading.value = false
            }

        }
    }
}