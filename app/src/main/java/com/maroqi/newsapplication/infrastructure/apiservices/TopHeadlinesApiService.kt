package com.maroqi.newsapplication.infrastructure.apiservices

import com.maroqi.newsapplication.application.utils.QueryUtil
import com.maroqi.newsapplication.domain.responses.NewsListResponse
import com.maroqi.newsapplication.infrastructure.network.RetrofitNetwork
import retrofit2.Call
import retrofit2.http.*

object TopHeadlinesApiService {
    interface ApiService {
        @GET("top-headlines")
        fun getUsers(@QueryMap queries: Map<String, String>): Call<NewsListResponse>
    }

    data class Query(
        val q: String? = null,
        val category: String? = null,
        val pageSize: String? = null,
        val page: String? = null,
    ) {
        fun create(): Map<String, String> = QueryUtil.toMapWithOnlyPrimaryConstructorProperties(this)
    }

    fun get(): ApiService {
        return RetrofitNetwork().client.create(ApiService::class.java)
    }
}
