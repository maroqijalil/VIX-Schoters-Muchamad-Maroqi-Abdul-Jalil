package com.maroqi.newsapplication.application.usecases

class UseCases(
    val getEverything: GetEverything,
    val getBookmarks: GetBookmarks,
    val insertBookmark: InsertBookmark,
    val deleteBookmark: DeleteBookmark,
    val getTopHeadlines: GetTopHeadlines,
    val filterNews: FilterNews
)
