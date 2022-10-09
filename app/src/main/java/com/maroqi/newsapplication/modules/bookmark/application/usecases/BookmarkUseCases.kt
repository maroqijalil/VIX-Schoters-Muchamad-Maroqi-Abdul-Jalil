package com.maroqi.newsapplication.modules.bookmark.application.usecases

data class BookmarkUseCases(
    val deleteBookmark: DeleteBookmark,
    val getBookmarks: GetBookmarks,
    val insertBookmark: InsertBookmark,
    val updateBookmark: UpdateBookmark
)
