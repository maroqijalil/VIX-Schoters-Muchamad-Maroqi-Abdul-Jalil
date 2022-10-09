package com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit

import com.maroqi.newsapplication.modules.news.infrastructure.responses.NewsListResponse
import retrofit2.http.*

interface EverythingApiService {
    @GET("everything")
    suspend fun getEverything(@QueryMap queries: Map<String, String>): NewsListResponse
}
