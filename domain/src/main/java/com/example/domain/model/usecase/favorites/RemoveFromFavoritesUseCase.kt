package com.example.domain.model.usecase.favorites

import com.example.domain.model.repository.FavoriteRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(url: String) {
        repository.deleteFavorite(url)
    }
}