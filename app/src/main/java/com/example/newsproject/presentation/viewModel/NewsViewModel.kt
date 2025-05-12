package com.example.newsproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewsArticle
import com.example.domain.model.usecase.GetNewsByCategoryUseCase
import com.example.domain.model.usecase.favorites.AddToFavoritesUseCase
import com.example.domain.model.usecase.favorites.GetFavoritesUseCase
import com.example.domain.model.usecase.favorites.IsFavoriteUseCase
import com.example.domain.model.usecase.favorites.RemoveFromFavoritesUseCase
import com.example.domain.model.usecase.history.DeleteHistoryUseCase
import com.example.domain.model.usecase.history.GetHistoryUseCase
import com.example.newsproject.presentation.NewsEvent
import com.example.newsproject.presentation.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    private val _favoriteFlow = MutableStateFlow<Boolean>(false)
    val favoriteFlow: StateFlow<Boolean> = _favoriteFlow.asStateFlow()

    private val _favoritesCache = mutableMapOf<String, Boolean>()
    private val _currentFavoriteState = MutableStateFlow(false)
    val currentFavoriteState: StateFlow<Boolean> = _currentFavoriteState.asStateFlow()


    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun onEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.LoadCategory -> loadCategoryNews(event.category)
            is NewsEvent.LoadHistory -> loadHistory()
            is NewsEvent.LoadFavorites -> loadFavorites()
            is NewsEvent.DeleteFromHistory -> deleteArticle(event.article)
            is NewsEvent.AddToFavorites -> addToFavorites(event.article)
            is NewsEvent.RemoveFromFavorites -> removeFromFavorites(event.url)
            is NewsEvent.DeleteFromFavorites -> deleteFavoriteArticle(event.url)
        }
    }

    fun loadFavoriteStatus(url: String) {
        viewModelScope.launch {
            _favoritesCache[url]?.let {
                _currentFavoriteState.value = it
                return@launch
            }

            val result = withContext(Dispatchers.IO) {
                isFavoriteUseCase(url)
            }
            _favoritesCache[url] = result
            _currentFavoriteState.value = result
        }
    }




    private fun addToFavorites(article: NewsArticle) {
        viewModelScope.launch {
            addToFavoritesUseCase(article)
            _isFavorite.value = true
        }
    }

    private fun removeFromFavorites(url: String) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(url)
            _isFavorite.value = false
        }
    }

    private fun loadCategoryNews(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val news = getNewsByCategoryUseCase(category)
                _uiState.update { it.copy(isLoading = false, news = news) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Ошибка загрузки данных")
                }
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getHistoryUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { articles ->
                    _uiState.update { it.copy(isLoading = false, news = articles) }
                }
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getFavoritesUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { articles ->
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

    fun toggleFavorite(article: NewsArticle) {
        viewModelScope.launch {
            val currentState = _currentFavoriteState.value
            if (currentState) {
                removeFromFavoritesUseCase(article.url)
            } else {
                addToFavoritesUseCase(article)
            }
            val newState = !currentState
            _favoritesCache[article.url] = newState
            _currentFavoriteState.value = newState
        }
    }

    private fun deleteFavoriteArticle(url: String) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(url)
            loadFavorites()
        }
    }


}
