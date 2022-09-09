package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.domain.requests.Request
import com.maroqi.newsapplication.domain.responses.NewsListResponse
import com.maroqi.newsapplication.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.infrastructure.apiservices.EverythingApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class GetEverything : UseCase<List<NewsModel>>() {
    override suspend fun execute(request: Request<List<NewsModel>>) {
        try {
            val response = EverythingApiService.get().getEverything(request.query)
            request.onSuccess(response.articles?.mapNotNull { NewsAdapter.toModel(it) }
                ?: listOf())
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
