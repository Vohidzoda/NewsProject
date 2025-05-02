package com.example.data.mapper

import com.example.data.model.NewsArticleUiModel
import com.example.domain.model.NewsArticle

fun NewsArticle.toUiModel() = NewsArticleUiModel(
    title = title,
    description = description,
    urlToImage = urlToImage,
    url = url,
    publishedAt = publishedAt
)
