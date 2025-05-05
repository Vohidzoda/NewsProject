package com.example.data.mapper

import com.example.data.entity.HistoryEntity
import com.example.domain.model.NewsArticle

fun NewsArticle.toEntity(): HistoryEntity = HistoryEntity(
    title = title,
    description = description,
    urlToImage = urlToImage,
    url = url,
    publishedAt = publishedAt
)

fun HistoryEntity.toDomain(): NewsArticle = NewsArticle(
    title = title,
    description = description,
    urlToImage = urlToImage,
    url = url,
    publishedAt = publishedAt
)