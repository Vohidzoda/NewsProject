package com.example.data.mapper

import com.example.data.model.NewsArticleUiModel
import com.example.domain.model.NewsArticle

fun NewsArticleUiModel.toDomain(): NewsArticle {
    return NewsArticle(
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        url = this.url,
        publishedAt = this.publishedAt
    )
}

fun NewsArticle.toUiModel(): NewsArticleUiModel {
    return NewsArticleUiModel(
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        url = this.url,
        publishedAt = this.publishedAt
    )
}
