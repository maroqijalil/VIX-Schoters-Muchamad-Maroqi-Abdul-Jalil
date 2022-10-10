package com.maroqi.newsapplication.modules.news.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import com.maroqi.newsapplication.modules.bookmark.application.usecases.GetBookmarks
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FilterNews @Inject constructor(private val dao: NewsDao) : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        val data = request.data?.map {
            it.isBookmarked = false
            it
        }

        GetBookmarks(dao)(
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
