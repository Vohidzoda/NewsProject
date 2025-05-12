package com.example.data.mapper

import com.example.data.entity.FavoriteEntity
import com.example.domain.model.NewsArticle

fun NewsArticle.toFavoriteEntity() = FavoriteEntity(
    url = url,
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)

fun FavoriteEntity.toDomain(): NewsArticle {
    return NewsArticle(
        url = this.url,
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt
    )
}
