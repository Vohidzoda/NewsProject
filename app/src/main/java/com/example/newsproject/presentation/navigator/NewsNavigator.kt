package com.example.newsproject.presentation.navigator

import com.example.domain.model.NewsArticle

interface NewsNavigator {
    fun navigateToDetail(article: NewsArticle)
}
