package com.example.domain.model.usecase.history

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.HistoryRepository
import javax.inject.Inject

class DeleteHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(article: NewsArticle) {
        repository.delete(article)
    }
}
