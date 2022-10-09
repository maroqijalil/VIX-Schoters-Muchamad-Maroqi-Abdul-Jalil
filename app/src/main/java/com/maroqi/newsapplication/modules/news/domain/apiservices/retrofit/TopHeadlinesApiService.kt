package com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit

import com.maroqi.newsapplication.modules.news.infrastructure.responses.NewsListResponse
import retrofit2.http.*

interface TopHeadlinesApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@QueryMap queries: Map<String, String>): NewsListResponse
}
