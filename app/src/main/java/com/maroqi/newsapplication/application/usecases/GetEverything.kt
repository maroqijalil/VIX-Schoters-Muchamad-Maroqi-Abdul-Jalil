package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.domain.requests.Request
import com.maroqi.newsapplication.domain.responses.NewsListResponse
import com.maroqi.newsapplication.infrastructure.adapters.NewsAdapter
import com.maroqi.newsapplication.infrastructure.apiservices.EverythingApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEverything {
    fun execute(request: Request<List<NewsModel>>) {
        EverythingApiService.get().getEverything(request.query)
            .enqueue(object : Callback<NewsListResponse> {
                override fun onResponse(
                    call: Call<NewsListResponse>,
                    response: Response<NewsListResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        request.onSuccess(body.articles?.mapNotNull { NewsAdapter.toModel(it) }
                            ?: listOf())
                    } else {
                        request.onFailure("There is some problem, try again!")
                    }
                }

                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) =
                    request.onFailure(t.message ?: "")
            })
    }
}
