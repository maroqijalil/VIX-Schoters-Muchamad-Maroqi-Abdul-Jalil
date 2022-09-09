package com.maroqi.newsapplication.domain.repositories.room

import androidx.room.*
import com.maroqi.newsapplication.domain.entities.NewsEntity

@Dao
interface NewsRepository {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>
}
