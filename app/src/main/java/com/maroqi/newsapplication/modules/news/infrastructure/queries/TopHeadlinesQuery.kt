package com.maroqi.newsapplication.modules.news.infrastructure.queries

import com.maroqi.newsapplication.core.utils.MapUtil

data class TopHeadlinesQuery(
    val q: String? = null,
    val country: String? = null,
    val category: String? = null,
    val pageSize: String? = null,
    val page: String? = null,
) {
    fun create(): Map<String, String> = MapUtil.toMap(this)
}
