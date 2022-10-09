package com.maroqi.newsapplication.modules.news.application.di

import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import com.maroqi.newsapplication.modules.news.infrastructure.db.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DaoModule {
    @Provides
    fun provideNewsDao(db: RoomDb): NewsDao {
        return db.newsDao()
    }
}
