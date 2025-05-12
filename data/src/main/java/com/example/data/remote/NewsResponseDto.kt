package com.example.data.remote

data class NewsResponseDto (
// TODO: add other fields if needed, example totalResults, status
    val articles: List<NewsArticleDto>
)

data class NewsArticleDto(
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String?,
    val publishedAt: String?
)
