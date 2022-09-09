package com.maroqi.newsapplication.infrastructure.apiservices.retrofit.queries

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
    fun create(): Map<String, String> {
        val map = mutableMapOf<String, String>()

        if (!this.q.isNullOrEmpty()) {
            map["q"] = this.q
        }
        if (!this.searchIn.isNullOrEmpty()) {
            map["searchIn"] = this.searchIn
        }
        if (!this.from.isNullOrEmpty()) {
            map["from"] = this.from
        }
        if (!this.to.isNullOrEmpty()) {
            map["to"] = this.to
        }
        if (!this.sortBy.isNullOrEmpty()) {
            map["sortBy"] = this.sortBy
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
