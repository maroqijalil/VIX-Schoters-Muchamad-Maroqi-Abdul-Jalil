package com.maroqi.newsapplication.modules.bookmark.application.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
data class BookmarkUseCases @Inject constructor(
    val deleteBookmark: DeleteBookmark,
    val getBookmarks: GetBookmarks,
    val insertBookmark: InsertBookmark,
    val updateBookmark: UpdateBookmark
)
