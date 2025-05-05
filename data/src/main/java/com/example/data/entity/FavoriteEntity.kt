package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
)