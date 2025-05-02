package com.example.domain.model.usecase

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.NewsRepository
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(category: String): List<NewsArticle>{
        return repository.getNewsByCategory(category)
    }
}