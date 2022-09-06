package com.maroqi.newsapplication.presentation.views.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.maroqi.newsapplication.databinding.ItemNewsListBinding
import com.maroqi.newsapplication.domain.responses.NewsResponse

class NewsListAdapter(private val values: List<NewsResponse>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsResponse) {
        }
    }
}
