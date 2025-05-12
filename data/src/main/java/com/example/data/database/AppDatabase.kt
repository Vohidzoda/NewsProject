package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.FavoriteDao
import com.example.data.dao.HistoryDao
import com.example.data.entity.HistoryEntity
import com.example.data.entity.FavoriteEntity

@Database(
    entities = [HistoryEntity::class, FavoriteEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    abstract fun favoriteDao(): FavoriteDao
}
