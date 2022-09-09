package com.maroqi.newsapplication.domain.apiservices.retrofit

import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.responses.NewsListResponse
import retrofit2.http.*

interface EverythingApiService {
    @GET("everything")
    suspend fun getEverything(@QueryMap queries: Map<String, String>): NewsListResponse
}
