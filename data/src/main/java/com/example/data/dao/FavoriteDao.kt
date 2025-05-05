package com.example.data.dao

import androidx.room.*
import com.example.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(article: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(article: FavoriteEntity)

    @Query("SELECT * FROM favorite ORDER BY publishedAt DESC")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE url = :url)")
    suspend fun isFavorite(url: String): Boolean

    @Query("DELETE FROM favorite WHERE url = :url")
    suspend fun deleteByUrl(url: String)
}
