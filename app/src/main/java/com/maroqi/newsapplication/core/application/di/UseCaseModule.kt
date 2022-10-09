package com.maroqi.newsapplication.core.application.di

import android.content.Context
import androidx.room.Room
import com.maroqi.newsapplication.core.application.usecases.UseCases
import com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit.EverythingApiService
import com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit.TopHeadlinesApiService
import com.maroqi.newsapplication.modules.news.infrastructure.db.RoomDb
import com.maroqi.newsapplication.core.network.RetrofitNetwork
import com.maroqi.newsapplication.modules.bookmark.application.usecases.DeleteBookmark
import com.maroqi.newsapplication.modules.bookmark.application.usecases.GetBookmarks
import com.maroqi.newsapplication.modules.bookmark.application.usecases.InsertBookmark
import com.maroqi.newsapplication.modules.news.application.usecases.FilterNews
import com.maroqi.newsapplication.modules.news.application.usecases.GetEverything
import com.maroqi.newsapplication.modules.news.application.usecases.GetTopHeadlines
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(context, RoomDb::class.java, "news_database").build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitNetwork.create()
    }

    @Provides
    fun provideUseCase(database: RoomDb, client: Retrofit): UseCases {
        val everythingApiService = client.create(EverythingApiService::class.java)
        val topHeadlinesApiService = client.create(TopHeadlinesApiService::class.java)

        return UseCases(
            GetEverything(everythingApiService, database.newsDao()),
            GetBookmarks(database.newsDao()),
            InsertBookmark(database.newsDao()),
            DeleteBookmark(database.newsDao()),
            GetTopHeadlines(topHeadlinesApiService, database.newsDao()),
            FilterNews(database.newsDao())
        )
    }
}
