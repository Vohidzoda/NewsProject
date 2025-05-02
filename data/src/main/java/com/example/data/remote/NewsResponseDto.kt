package com.example.data.remote

data class NewsResponseDto (
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String?,
    val publishedAt: String?
)
