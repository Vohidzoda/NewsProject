package com.example.data.repository

import com.example.data.api.NewsApiService
import com.example.data.mapper.toDomain
import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService
) : NewsRepository {

    override suspend fun getNewsByCategory(category: String): List<NewsArticle> {
        val response = api.getTopHeadlines(category = category)
        return response.articles.map { it.toDomain(category) }
    }
}