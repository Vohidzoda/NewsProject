package com.example.domain.model.repository

import com.example.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insert(news: NewsArticle)
    fun getAllHistory(): Flow<List<NewsArticle>>
    suspend fun delete(news: NewsArticle)

}