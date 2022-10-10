package com.maroqi.newsapplication.modules.news.application.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
data class NewsUseCases @Inject constructor(
    val filterNews: FilterNews,
    val getEverything: GetEverything,
    val getTopHeadlines: GetTopHeadlines
)
