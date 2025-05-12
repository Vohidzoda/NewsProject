package com.example.newsproject.presentation

import com.example.domain.model.NewsArticle

sealed class NewsEvent {
    data class LoadCategory(val category: String) : NewsEvent()
    object LoadHistory : NewsEvent()
    data class DeleteFromHistory(val article: NewsArticle) : NewsEvent()
    data class AddToFavorites(val article: NewsArticle) : NewsEvent()
    data class RemoveFromFavorites(val url: String) : NewsEvent()
    data class DeleteFromFavorites(val url: String) : NewsEvent()

    object LoadFavorites : NewsEvent()
}
