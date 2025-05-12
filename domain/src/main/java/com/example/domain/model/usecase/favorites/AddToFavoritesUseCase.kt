package com.example.domain.model.usecase.favorites

import com.example.domain.model.NewsArticle
import com.example.domain.model.repository.FavoriteRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(article: NewsArticle) {
        repository.insertFavorite(article)
    }
}