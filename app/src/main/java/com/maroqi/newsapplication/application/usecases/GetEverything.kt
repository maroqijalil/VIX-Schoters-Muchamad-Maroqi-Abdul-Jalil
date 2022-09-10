package com.maroqi.newsapplication.application.usecases

import android.util.Log
import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.domain.apiservices.retrofit.EverythingApiService
import com.maroqi.newsapplication.domain.repositories.room.NewsRepository
import java.lang.Exception

class GetEverything(
    private val apiService: EverythingApiService,
    private val repository: NewsRepository
) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            val response = apiService.getEverything(request.query)

            GetBookmarks(repository)(
                Request(
                    onSuccess = { bookmarks ->
                        request.onSuccess(response.articles?.mapNotNull {
                            val item = NewsAdapter.toModel(it)
                            bookmarks?.forEach { bookmark ->
                                if (bookmark.title.equals(item.title)) {
                                    item.isBookmarked = true
                                }
                            }
                            item
                        } ?: listOf())
                    },
                    onFailure = request.onFailure
                )
            )
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
