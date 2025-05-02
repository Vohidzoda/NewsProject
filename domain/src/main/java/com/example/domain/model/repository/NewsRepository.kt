package com.example.domain.model.repository

import com.example.domain.model.NewsArticle

interface NewsRepository {
    suspend fun getNewsByCategory(category: String): List<NewsArticle>
}