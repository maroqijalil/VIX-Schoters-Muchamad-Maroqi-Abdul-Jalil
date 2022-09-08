package com.maroqi.newsapplication.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maroqi.newsapplication.application.usecases.GetEverything
import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.domain.requests.Request
import com.maroqi.newsapplication.infrastructure.apiservices.EverythingApiService

class MainViewModel : ViewModel() {
    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> = _news

    fun getNews() {
        GetEverything().execute(Request(
            query = EverythingApiService.Query(
                language = "en",
                q = "a",
                pageSize = "10",
            ).create(),
            onSuccess = { _news.value = it },
            onFailure = {}
        ))
    }
}
