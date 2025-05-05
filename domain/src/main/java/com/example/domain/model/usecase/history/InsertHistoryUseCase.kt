package com.example.domain.model.usecase.history

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.HistoryRepository
import javax.inject.Inject

class InsertHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(article: NewsArticle){
        historyRepository.insert(article)
    }
}