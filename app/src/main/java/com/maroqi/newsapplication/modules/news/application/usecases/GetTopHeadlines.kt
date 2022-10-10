package com.maroqi.newsapplication.modules.news.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.domain.apiservices.retrofit.NewsApiService
import com.maroqi.newsapplication.modules.news.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import dagger.hilt.android.scopes.ViewModelScoped
import java.lang.Exception
import javax.inject.Inject

@ViewModelScoped
class GetTopHeadlines @Inject constructor(
    private val apiService: NewsApiService,
    private val dao: NewsDao
) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            val response = apiService.getTopHeadlines(request.query)
            request.data = response.articles?.mapNotNull { NewsAdapter.toModel(it) }
            FilterNews(dao)(request)
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
