package com.maroqi.newsapplication.core.application.di

import android.content.Context
import androidx.room.Room
import com.maroqi.newsapplication.modules.news.infrastructure.db.RoomDb
import com.maroqi.newsapplication.core.network.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideRoom(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(context, RoomDb::class.java, "news_database").build()
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitNetwork.create()
    }
}
