package com.maroqi.newsapplication.modules.bookmark.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import java.lang.Exception

class GetBookmarks(private val repository: NewsDao) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            request.onSuccess(repository.getAll().map { NewsAdapter.toModel(it) })
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
