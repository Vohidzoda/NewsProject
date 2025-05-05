package com.example.data.remote

data class NewsResponseDto (
    val articles: List<NewsArticleDto>
)

data class NewsArticleDto(
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String?,
    val publishedAt: String?
)
