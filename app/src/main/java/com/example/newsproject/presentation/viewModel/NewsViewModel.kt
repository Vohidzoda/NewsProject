package com.example.newsproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewsArticle
import com.example.domain.model.usecase.GetNewsByCategoryUseCase
import com.example.domain.model.usecase.history.DeleteHistoryUseCase
import com.example.domain.model.usecase.history.GetHistoryUseCase
import com.example.newsproject.R
import com.example.newsproject.presentation.NewsEvent
import com.example.newsproject.presentation.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    fun onEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.LoadCategory -> loadCategoryNews(event.category)
            is NewsEvent.LoadHistory -> loadHistory()
            is NewsEvent.DeleteFromHistory -> deleteArticle(event.article)
        }
    }

    private fun loadCategoryNews(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val news = getNewsByCategoryUseCase(category)
                _uiState.update { it.copy(isLoading = false, news = news) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message ?: R.string.error_loading.toString()) }
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getHistoryUseCase().catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }.collect { articles ->
                _uiState.update { it.copy(isLoading = false, news = articles) }
            }
        }
    }

    private fun deleteArticle(article: NewsArticle) {
        viewModelScope.launch {
            deleteHistoryUseCase(article)
            loadHistory()
        }
    }

}
