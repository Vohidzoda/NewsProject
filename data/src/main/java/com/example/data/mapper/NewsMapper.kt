package com.example.data.mapper

import com.example.data.remote.NewsArticleDto
import com.example.domain.model.NewsArticle

fun NewsArticleDto.toDomain(category: String): NewsArticle {
    return NewsArticle(
        title = title ?: "No Title",
        description = description ?: "No Description",
        urlToImage = urlToImage ?: "",
        url = url ?: "",
        publishedAt = publishedAt ?: ""
    )
}
