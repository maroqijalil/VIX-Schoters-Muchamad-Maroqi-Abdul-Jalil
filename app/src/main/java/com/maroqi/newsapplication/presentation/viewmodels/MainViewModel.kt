package com.maroqi.newsapplication.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maroqi.newsapplication.application.usecases.UseCases
import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests.Request
import com.maroqi.newsapplication.infrastructure.apiservices.retrofit.queries.EverythingQuery

class MainViewModel(private val useCases: UseCases) : ViewModel() {
    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> = _news

    fun getNews(query: String, pageSize: Int) {
        useCases.getEverything(
            Request(
                query = EverythingQuery(
                    language = "en",
                    q = query,
                    pageSize = pageSize.toString(),
                ).create(),
                onSuccess = { _news.value = it },
                onFailure = {}
            ),
            viewModelScope
        )
    }
}
