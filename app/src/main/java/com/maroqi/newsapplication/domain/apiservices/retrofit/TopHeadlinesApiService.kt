package com.maroqi.newsapplication.domain.apiservices.retrofit

import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.responses.NewsListResponse
import retrofit2.http.*

interface TopHeadlinesApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@QueryMap queries: Map<String, String>): NewsListResponse
}
