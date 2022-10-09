package com.maroqi.newsapplication.core.network

data class Request <T>(
    var data: T? = null,
    val query: Map<String, String> = mapOf(),
    val onSuccess: (result: T?) -> Unit,
    val onFailure: (message: String) -> Unit
)
