package com.example.domain.model.usecase.favorites

import com.example.domain.model.repository.FavoriteRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return repository.isFavorite(url)
    }
}