package com.example.data.repository

import com.example.data.dao.FavoriteDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toFavoriteEntity
import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository {

    override fun getAllFavoritesFlow(): Flow<List<NewsArticle>> {
        return dao.getAllFavoritesFlow().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getFavoriteByUrl(url: String): NewsArticle? {
        return dao.getByUrl(url)?.toDomain()
    }

    override suspend fun isFavorite(url: String): Boolean {
        return dao.isFavorite(url)
    }

    override suspend fun insertFavorite(article: NewsArticle) {
        dao.insert(article.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(url: String) {
        dao.deleteByUrl(url)
    }
}