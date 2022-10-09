package com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit

import com.maroqi.newsapplication.modules.news.infrastructure.responses.NewsListResponse
import retrofit2.http.*

interface NewsApiService {
    @GET("everything")
    suspend fun getEverything(@QueryMap queries: Map<String, String>): NewsListResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(@QueryMap queries: Map<String, String>): NewsListResponse
}
