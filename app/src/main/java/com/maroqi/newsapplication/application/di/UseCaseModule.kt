package com.maroqi.newsapplication.application.di

import android.content.Context
import androidx.room.Room
import com.maroqi.newsapplication.application.usecases.*
import com.maroqi.newsapplication.domain.apiservices.retrofit.EverythingApiService
import com.maroqi.newsapplication.domain.apiservices.retrofit.TopHeadlinesApiService
import com.maroqi.newsapplication.infrastructure.db.room.RoomDb
import com.maroqi.newsapplication.infrastructure.network.retorfit.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(context, RoomDb::class.java, "news_database").build()
    }

    @Provides
    @Singleton
    fun provideNetwork(): Retrofit {
        return RetrofitNetwork().client
    }

    @Provides
    fun provideUseCase(database: RoomDb, client: Retrofit): UseCases {
        val everythingApiService = client.create(EverythingApiService::class.java)
        val topHeadlinesApiService = client.create(TopHeadlinesApiService::class.java)

        return UseCases(
            GetEverything(everythingApiService, database.newsRepository()),
            GetBookmarks(database.newsRepository()),
            InsertBookmark(database.newsRepository()),
            DeleteBookmark(database.newsRepository()),
            GetTopHeadlines(topHeadlinesApiService, database.newsRepository()),
            FilterNews(database.newsRepository())
        )
    }
}
