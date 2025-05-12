package com.example.domain.model.usecase.favorites

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<NewsArticle>> {
        return repository.getAllFavoritesFlow()
    }
}