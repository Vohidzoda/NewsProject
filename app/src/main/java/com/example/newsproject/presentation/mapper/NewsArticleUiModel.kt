package com.example.newsproject.presentation.mapper

import com.example.data.model.NewsArticleUiModel
import com.example.domain.model.NewsArticle

fun NewsArticleUiModel.toDomain(): NewsArticle = NewsArticle(
    title = title,
    description = description,
    urlToImage = urlToImage,
    url = url,
    publishedAt = publishedAt
)
