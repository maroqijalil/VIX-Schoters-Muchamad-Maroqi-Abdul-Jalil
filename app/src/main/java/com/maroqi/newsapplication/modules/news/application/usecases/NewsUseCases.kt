package com.maroqi.newsapplication.modules.news.application.usecases

data class NewsUseCases(
    val filterNews: FilterNews,
    val getEverything: GetEverything,
    val getTopHeadlines: GetTopHeadlines
)
