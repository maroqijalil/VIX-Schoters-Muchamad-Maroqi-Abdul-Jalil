package com.maroqi.newsapplication.infrastructure.apiservices.retrofit.queries

data class TopHeadlinesQuery(
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
