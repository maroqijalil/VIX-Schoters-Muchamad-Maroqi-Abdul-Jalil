package com.maroqi.newsapplication.modules.news.application.di

import com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ApiServiceModule {
    @Provides
    fun provideNewsApiService(client: Retrofit): NewsApiService {
        return client.create(NewsApiService::class.java)
    }
}
