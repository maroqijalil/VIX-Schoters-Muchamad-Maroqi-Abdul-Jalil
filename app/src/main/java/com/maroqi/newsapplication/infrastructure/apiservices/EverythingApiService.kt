package com.maroqi.newsapplication.infrastructure.apiservices

import com.maroqi.newsapplication.application.utils.QueryUtil
import com.maroqi.newsapplication.domain.responses.NewsListResponse
import com.maroqi.newsapplication.infrastructure.network.RetrofitNetwork
import retrofit2.Call
import retrofit2.http.*

object EverythingApiService {
    interface ApiService {
        @GET("everything")
        fun getUsers(@QueryMap queries: Map<String, String>): Call<NewsListResponse>
    }

    data class Query(
        val q: String? = null,
        val searchIn: String? = null,
        val from: String? = null,
        val to: String? = null,
        val sortBy: String? = null,
        val pageSize: String? = null,
        val page: String? = null,
    ) {
        fun create(): Map<String, String> = QueryUtil.toMapWithOnlyPrimaryConstructorProperties(this)
    }

    fun get(): ApiService {
        return RetrofitNetwork().client.create(ApiService::class.java)
    }
}
