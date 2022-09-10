package com.maroqi.newsapplication.application.usecases

data class UseCases(
    val getEverything: GetEverything,
    val getBookmarks: GetBookmarks,
    val insertBookmark: InsertBookmark,
    val deleteBookmark: DeleteBookmark,
    val getTopHeadlines: GetTopHeadlines
)
