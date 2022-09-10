package com.maroqi.newsapplication.domain.repositories.room

import androidx.room.*
import com.maroqi.newsapplication.domain.entities.NewsEntity

@Dao
interface NewsRepository {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: NewsEntity)

    @Query("DELETE FROM news WHERE title=:title")
    fun delete(title: String)

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>
}
