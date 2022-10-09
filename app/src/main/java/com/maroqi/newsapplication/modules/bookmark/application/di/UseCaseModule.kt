package com.maroqi.newsapplication.modules.bookmark.application.di

import com.maroqi.newsapplication.modules.bookmark.application.usecases.*
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideBookmarkUseCases(dao: NewsDao): BookmarkUseCases {
        return BookmarkUseCases(
            DeleteBookmark(dao),
            GetBookmarks(dao),
            InsertBookmark(dao),
            UpdateBookmark(dao)
        )
    }
}
