package com.maroqi.newsapplication.modules.news.presentation.views.adapters

import androidx.recyclerview.widget.DiffUtil
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel

class NewsListDiffCallback(private val old: List<NewsModel>, private val new: List<NewsModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].title.equals(new[newItemPosition].title)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].isBookmarked == new[newItemPosition].isBookmarked
}
