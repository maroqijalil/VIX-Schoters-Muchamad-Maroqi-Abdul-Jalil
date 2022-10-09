package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository

class FilterNews(private val repository: NewsRepository) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        val data = request.data?.map {
            it.isBookmarked = false
            it
        }

        GetBookmarks(repository)(
            Request(
                onSuccess = { bookmarks ->
                    request.onSuccess(data?.map { news ->
                        bookmarks?.forEach { bookmark ->
                            if (bookmark.title.equals(news.title)) {
                                news.isBookmarked = true
                            }
                        }
                        news
                    } ?: listOf())
                },
                onFailure = request.onFailure
            )
        )
    }
}
