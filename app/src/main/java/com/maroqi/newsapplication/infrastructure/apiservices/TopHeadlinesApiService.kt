package com.maroqi.newsapplication.infrastructure.apiservices

import com.maroqi.newsapplication.domain.responses.NewsListResponse
import com.maroqi.newsapplication.infrastructure.network.RetrofitNetwork
import retrofit2.Call
import retrofit2.http.*

object TopHeadlinesApiService {
    interface ApiService {
        @GET("top-headlines")
        fun getTopHeadlines(@QueryMap queries: Map<String, String>): Call<NewsListResponse>
    }

    data class Query(
        val q: String? = null,
        val category: String? = null,
        val pageSize: String? = null,
        val page: String? = null,
    ) {
        fun create(): Map<String, String>  {
            val map = mutableMapOf<String, String>()

            if (!this.q.isNullOrEmpty()) {
                map["q"] = this.q
            }
            if (!this.category.isNullOrEmpty()) {
                map["category"] = this.category
            }
            if (!this.pageSize.isNullOrEmpty()) {
                map["pageSize"] = this.pageSize
            }
            if (!this.page.isNullOrEmpty()) {
                map["page"] = this.page
            }

            return map
        }
    }

    fun get(): ApiService {
        return RetrofitNetwork().client.create(ApiService::class.java)
    }
}
