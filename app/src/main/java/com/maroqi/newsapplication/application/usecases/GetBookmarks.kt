package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository
import java.lang.Exception

class GetBookmarks(private val repository: NewsRepository) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            request.onSuccess(repository.getAll().map { NewsAdapter.toModel(it) })
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
