package com.maroqi.newsapplication.domain.requests

data class Request <in T>(
    val query: Map<String, String> = mapOf(),
    val onSuccess: (result: T) -> Unit,
    val onFailure: (message: String) -> Unit
)
