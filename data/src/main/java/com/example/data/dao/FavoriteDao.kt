package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Query("SELECT * FROM favorite WHERE url = :url LIMIT 1")
    suspend fun getByUrl(url: String): FavoriteEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE url = :url)")
    suspend fun isFavorite(url: String): Boolean

    @Query("SELECT * FROM favorite ORDER BY id DESC")
    fun getAllFavoritesFlow(): Flow<List<FavoriteEntity>>
}