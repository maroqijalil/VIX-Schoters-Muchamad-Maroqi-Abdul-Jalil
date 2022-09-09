package com.maroqi.newsapplication.presentation.views.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.maroqi.newsapplication.R
import com.maroqi.newsapplication.databinding.ItemNewsListBinding
import com.maroqi.newsapplication.domain.models.NewsModel
import java.text.SimpleDateFormat

class NewsListAdapter(
    values: List<NewsModel>,
    private val onClick: (item: NewsModel) -> Unit = {},
    private val onClickBookmark: (item: NewsModel) -> Unit = {},
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list: MutableList<NewsModel> = values.toMutableList()

    inner class ViewHolder(val binding: ItemNewsListBinding) :
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

    inner class DiffCallback(private val old: List<NewsModel>, private val new: List<NewsModel>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition].title.equals(new[newItemPosition].title)

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition].isBookmarked == new[newItemPosition].isBookmarked

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.binding.igNewsFavorite.apply {
            if (list[position].isBookmarked) {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_bookmark_24
                    )
                )
            } else {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_bookmark_border_24
                    )
                )
            }

            isClickable = true
            setOnClickListener {
                val item = list[position]

                if (item.isBookmarked) {
                    this.setImageDrawable(
                        ContextCompat.getDrawable(
                            this.context,
                            R.drawable.ic_bookmark_border_24
                        )
                    )
                } else {
                    this.setImageDrawable(
                        ContextCompat.getDrawable(
                            this.context,
                            R.drawable.ic_bookmark_24
                        )
                    )
                }

                item.isBookmarked = !item.isBookmarked
                onClickBookmark(item)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun changeList(list: List<NewsModel>) {
        val diffCallback = DiffCallback(this.list, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}
