package com.example.data.mapper

import com.example.data.remote.ArticleDto
import com.example.domain.model.NewsArticle

fun ArticleDto.toDomain(category: String): NewsArticle {
    return NewsArticle(
        title = title ?: "No Title",
        description = description ?: "No Description",
        urlToImage = urlToImage ?: "",
        url = url ?: "",
        publishedAt = publishedAt ?: ""
    )
}
