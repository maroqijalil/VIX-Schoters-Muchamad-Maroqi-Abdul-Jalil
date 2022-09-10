package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository

class FilterNews(private val repository: NewsRepository) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        GetBookmarks(repository)(
            Request(
                onSuccess = { bookmarks ->
                    request.onSuccess(request.data?.map {
                        bookmarks?.forEach { bookmark ->
                            if (bookmark.title.equals(it.title)) {
                                it.isBookmarked = true
                            }
                        }
                        it
                    } ?: listOf())
                },
                onFailure = request.onFailure
            )
        )
    }
}
