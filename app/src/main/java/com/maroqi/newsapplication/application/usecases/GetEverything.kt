package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.adapters.NewsAdapter
import com.maroqi.newsapplication.domain.apiservices.retrofit.EverythingApiService
import java.lang.Exception

class GetEverything(private val apiService: EverythingApiService) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            val response = apiService.getEverything(request.query)
            request.onSuccess(response.articles?.mapNotNull { NewsAdapter.toModel(it) }
                ?: listOf())
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
