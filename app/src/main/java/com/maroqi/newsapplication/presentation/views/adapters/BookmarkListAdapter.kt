package com.maroqi.newsapplication.presentation.views.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.maroqi.newsapplication.databinding.ItemBookmarkListBinding
import com.maroqi.newsapplication.domain.models.NewsModel

class BookmarkListAdapter(
    values: List<NewsModel>,
    private val onClick: (item: NewsModel) -> Unit = {},
    private val onClickBookmark: (item: NewsModel) -> Unit = {},
) :
    RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    private val list: MutableList<NewsModel> = values.toMutableList()

    inner class ViewHolder(val binding: ItemBookmarkListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsModel) {
            binding.tvNewsTitle.text = item.title
            binding.tvNewsDesc.text = item.description
            binding.tvNewsAuthor.text = item.author
            binding.tvNewsDate.text = item.getDate()

            if (!item.image.isNullOrEmpty()) {
                Glide.with(binding.root)
                    .load(item.image)
                    .into(binding.ivNewsItem)
            }

            binding.llNewsItem.apply {
                isClickable = true
                setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookmarkListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.binding.igNewsFavorite.apply {
            isClickable = true
            setOnClickListener {
//                onClickBookmark(list[position])
                removeAt(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun changeList(list: List<NewsModel>) {
        val diffCallback = NewsListDiffCallback(this.list, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }
}
