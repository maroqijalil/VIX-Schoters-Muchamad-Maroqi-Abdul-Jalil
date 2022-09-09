package com.maroqi.newsapplication.domain.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.maroqi.newsapplication.domain.entities.NewsEntity

@Dao
interface NewsRepository {
    @Insert
    fun insert(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)

    @Query("SELECT * FROM news")
    fun getAll(): LiveData<List<NewsEntity>>
}
