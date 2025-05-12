package com.example.domain.model.repository

import com.example.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavoritesFlow(): Flow<List<NewsArticle>>
    suspend fun getFavoriteByUrl(url: String): NewsArticle?
    suspend fun isFavorite(url: String): Boolean
    suspend fun insertFavorite(article: NewsArticle)
    suspend fun deleteFavorite(url: String)
}