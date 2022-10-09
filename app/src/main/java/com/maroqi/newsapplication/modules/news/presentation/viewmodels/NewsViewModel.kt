package com.maroqi.newsapplication.modules.news.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.bookmark.application.usecases.BookmarkUseCases
import com.maroqi.newsapplication.modules.news.application.usecases.NewsUseCases
import com.maroqi.newsapplication.modules.news.infrastructure.queries.EverythingQuery
import com.maroqi.newsapplication.modules.news.infrastructure.queries.TopHeadlinesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCases: NewsUseCases,
    private val bookmarkUseCases: BookmarkUseCases
) : ViewModel() {
    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> = _news

    fun getNews(query: String = "a") {
        useCases.getEverything(
            Request(
                query = EverythingQuery(
                    language = "en",
                    q = query,
                ).create(),
                onSuccess = { _news.postValue(it) },
                onFailure = {}
            ),
            viewModelScope
        )
    }

    fun getHeadlinesNews(country: String = "us") {
        useCases.getTopHeadlines(
            Request(
                query = TopHeadlinesQuery(
                    country = country,
                ).create(),
                onSuccess = { _news.postValue(it) },
                onFailure = {}
            ),
            viewModelScope
        )
    }

    fun refreshNews() {
        useCases.filterNews(
            Request(
                data = news.value,
                onSuccess = { _news.postValue(it) },
                onFailure = {}
            )
        )
    }

    fun bookmark(news: NewsModel) {
        bookmarkUseCases.updateBookmark(
            Request(
                data = news,
                onSuccess = {},
                onFailure = {}
            ),
            viewModelScope
        )
    }
}
