package com.maroqi.newsapplication.modules.news.infrastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maroqi.newsapplication.modules.news.domain.entities.room.NewsEntity
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao

@Database(entities = [NewsEntity::class], version = 1)
abstract class RoomDb: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
