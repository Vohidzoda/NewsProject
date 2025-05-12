package com.example.newsproject.presentation.state


import com.example.domain.model.NewsArticle

data class NewsUiState(
    val isLoading: Boolean = false,
    val news: List<NewsArticle> = emptyList(),
    val favorites: List<NewsArticle> = emptyList(),
    val error: String? = null
)
