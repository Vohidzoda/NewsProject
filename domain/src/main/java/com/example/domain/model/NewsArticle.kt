package com.example.domain.model

import java.sql.Timestamp


data class NewsArticle (
    val title: String,
    val description: String,
    val urlToImage: String,
    val url: String,
    val publishedAt: String,
    var isFavorite: Boolean = false
)