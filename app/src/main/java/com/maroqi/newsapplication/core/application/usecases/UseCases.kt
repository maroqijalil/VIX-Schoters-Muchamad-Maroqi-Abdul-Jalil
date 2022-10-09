package com.maroqi.newsapplication.core.application.usecases

import com.maroqi.newsapplication.modules.bookmark.application.usecases.DeleteBookmark
import com.maroqi.newsapplication.modules.bookmark.application.usecases.GetBookmarks
import com.maroqi.newsapplication.modules.bookmark.application.usecases.InsertBookmark
import com.maroqi.newsapplication.modules.news.application.usecases.FilterNews
import com.maroqi.newsapplication.modules.news.application.usecases.GetEverything
import com.maroqi.newsapplication.modules.news.application.usecases.GetTopHeadlines

class UseCases(
    val getEverything: GetEverything,
    val getBookmarks: GetBookmarks,
    val insertBookmark: InsertBookmark,
    val deleteBookmark: DeleteBookmark,
    val getTopHeadlines: GetTopHeadlines,
    val filterNews: FilterNews
)
