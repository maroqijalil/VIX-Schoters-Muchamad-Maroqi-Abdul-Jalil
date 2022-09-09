package com.maroqi.newsapplication.domain.apiservices

import com.maroqi.newsapplication.domain.apiservices.retrofit.EverythingApiService
import com.maroqi.newsapplication.domain.apiservices.retrofit.TopHeadlinesApiService

data class ApiServices(
    val everythingApiService: EverythingApiService,
    val topHeadlinesApiService: TopHeadlinesApiService
)
