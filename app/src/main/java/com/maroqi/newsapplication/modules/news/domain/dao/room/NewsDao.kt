package com.maroqi.newsapplication.modules.news.domain.dao.room

import androidx.room.*
import com.maroqi.newsapplication.modules.news.domain.entities.room.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: NewsEntity)

    @Query("DELETE FROM news WHERE title=:title")
    fun delete(title: String)

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>
}
