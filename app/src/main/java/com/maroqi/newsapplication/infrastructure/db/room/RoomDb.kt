package com.maroqi.newsapplication.infrastructure.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maroqi.newsapplication.domain.entities.NewsEntity
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository

@Database(entities = [NewsEntity::class], version = 1)
abstract class RoomDb: RoomDatabase() {
    abstract fun newsRepository(): NewsRepository
}
