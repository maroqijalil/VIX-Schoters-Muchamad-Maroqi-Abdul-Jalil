package com.maroqi.newsapplication.modules.news.application.di

import com.maroqi.newsapplication.modules.news.application.usecases.FilterNews
import com.maroqi.newsapplication.modules.news.application.usecases.GetEverything
import com.maroqi.newsapplication.modules.news.application.usecases.GetTopHeadlines
import com.maroqi.newsapplication.modules.news.application.usecases.NewsUseCases
import com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit.NewsApiService
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideNewsUseCases(apiService: NewsApiService, dao: NewsDao): NewsUseCases {
        return NewsUseCases(
            FilterNews(dao),
            GetEverything(apiService, dao),
            GetTopHeadlines(apiService, dao)
        )
    }
}
