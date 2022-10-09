package com.maroqi.newsapplication.modules.bookmark.presentation.viewmodels

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
class BookmarkViewModel @Inject constructor(private val useCases: BookmarkUseCases) : ViewModel() {
    private val _bookmarks = MutableLiveData<List<NewsModel>>()
    val bookmarks: LiveData<List<NewsModel>> = _bookmarks

    fun getBookmarks() {
        useCases.getBookmarks(
            Request(
                onSuccess = { _bookmarks.postValue(it) },
                onFailure = {}
            ),
            viewModelScope
        )
    }

    fun resetBookmarks() {
        _bookmarks.value = listOf()
    }

    fun bookmark(news: NewsModel) {
        useCases.updateBookmark(
            Request(
                data = news,
                onSuccess = {},
                onFailure = {}
            ),
            viewModelScope
        )
    }
}
