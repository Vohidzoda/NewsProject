package com.example.data.repository

import com.example.data.dao.HistoryDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
): HistoryRepository {

    override suspend fun insert(news: NewsArticle) {
        historyDao.insert(news.toEntity())
    }

    override fun getAllHistory(): Flow<List<NewsArticle>> {
        return historyDao.getAllHistory().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun delete(news: NewsArticle) {
        historyDao.deleteByUrl(news.url)
    }

    // TODO: Add method to clear all history
}