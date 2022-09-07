package com.maroqi.newsapplication.infrastructure.adapters

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.domain.responses.NewsResponse
import java.text.SimpleDateFormat
import java.util.*

object NewsAdapter {
    fun toModel(response: NewsResponse?): NewsModel {
        return NewsModel(
            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(response?.publishedAt)
                ?: Date(),
            author = response?.author ?: "",
            image = response?.urlToImage ?: "",
            description = response?.description ?: "",
            title = response?.title ?: "",
            content = response?.content ?: ""
        )
    }
}
