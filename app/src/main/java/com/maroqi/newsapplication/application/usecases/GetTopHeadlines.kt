package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.domain.apiservices.retrofit.TopHeadlinesApiService
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository
import java.lang.Exception

class GetTopHeadlines(
    private val apiService: TopHeadlinesApiService,
    private val repository: NewsRepository
) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            val response = apiService.getTopHeadlines(request.query)
            request.data = response.articles?.mapNotNull { NewsAdapter.toModel(it) }
            FilterNews(repository)(request)
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
