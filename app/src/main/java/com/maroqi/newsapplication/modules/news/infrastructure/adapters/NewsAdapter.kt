package com.maroqi.newsapplication.modules.news.infrastructure.adapters

import com.maroqi.newsapplication.modules.news.domain.entities.room.NewsEntity
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.modules.news.infrastructure.responses.NewsResponse
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter {
    companion object {
        fun toModel(response: NewsResponse?): NewsModel {
            return NewsModel(
                date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(response?.publishedAt)
                    ?: Date(),
                author = response?.author ?: "",
                image = response?.urlToImage ?: "",
                description = response?.description ?: "",
                title = response?.title ?: "",
                content = response?.content ?: "",
                url = response?.url
            )
        }

        fun toModel(entity: NewsEntity): NewsModel {
            return NewsModel(
                date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(entity.publishedAt)
                    ?: Date(),
                author = entity.author ?: "",
                image = entity.image ?: "",
                description = entity.description ?: "",
                title = entity.title ?: "",
                content = entity.content ?: "",
                url = entity.url
            )
        }

        fun fromModel(model: NewsModel): NewsEntity {
            return NewsEntity(
                title = model.title,
                author = model.author,
                content = model.content,
                description = model.description,
                image = model.image,
                url = model.url,
                publishedAt = model.getFullDate()
            )
        }
    }
}
