package com.example.domain.model.usecase.history

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    operator fun invoke(): Flow<List<NewsArticle>> {
        return historyRepository.getAllHistory()
    }
}