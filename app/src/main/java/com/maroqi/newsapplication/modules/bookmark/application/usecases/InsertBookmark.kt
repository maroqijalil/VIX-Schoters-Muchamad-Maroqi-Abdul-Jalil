package com.maroqi.newsapplication.modules.bookmark.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import java.lang.Exception

class InsertBookmark(private val repository: NewsDao) : UseCase<NewsModel>() {
    override suspend fun execute(request: Request<NewsModel>) {
        try {
            repository.insert(NewsAdapter.fromModel(request.data!!))
            request.onSuccess(null)
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
