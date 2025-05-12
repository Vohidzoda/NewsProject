package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticleUiModel(
    val title: String,
    // TODO: add nullable description
    val description: String,
    val urlToImage: String,
    val url: String,
    val publishedAt: String
) : Parcelable
