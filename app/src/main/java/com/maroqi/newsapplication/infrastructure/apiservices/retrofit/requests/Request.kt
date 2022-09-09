package com.maroqi.newsapplication.infrastructure.apiservices.retrofit.requests

data class Request <T>(
    val data: T? = null,
    val query: Map<String, String> = mapOf(),
    val onSuccess: (result: T?) -> Unit,
    val onFailure: (message: String) -> Unit
)
