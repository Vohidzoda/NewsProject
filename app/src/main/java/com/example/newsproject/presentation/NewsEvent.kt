package com.example.newsproject.presentation

import com.example.domain.model.NewsArticle

sealed class NewsEvent {
    data class LoadCategory(val category: String) : NewsEvent()
    object LoadHistory : NewsEvent()
    data class DeleteFromHistory(val article: NewsArticle) : NewsEvent()
}
