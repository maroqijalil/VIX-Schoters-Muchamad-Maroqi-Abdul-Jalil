package com.maroqi.newsapplication.infrastructure.adapters

import com.maroqi.newsapplication.domain.models.NewsModel
import com.maroqi.newsapplication.domain.responses.NewsResponse
import java.text.SimpleDateFormat

object NewsAdapter {
    fun toModel(response: NewsResponse): NewsModel {
        return NewsModel(
            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(response.publishedAt),
            author = response.author,
            image = response.urlToImage,
            description = response.description,
            title = response.title,
            content = response.content
        )
    }
}
