package com.maroqi.newsapplication.modules.news.infrastructure.queries

import com.maroqi.newsapplication.core.utils.MapUtil

data class EverythingQuery(
    val q: String? = null,
    val searchIn: String? = null,
    val from: String? = null,
    val to: String? = null,
    val sortBy: String? = null,
    val pageSize: String? = null,
    val page: String? = null,
    val language: String? = null,
) {
    fun create(): Map<String, String> = MapUtil.toMap(this)
}
