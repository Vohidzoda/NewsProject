package com.example.data.api

import com.example.data.remote.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("country") country: String = "us"
    ): NewsResponseDto
}