package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.maroqi.newsapplication.application.factory.NewsViewModelFactory
import com.maroqi.newsapplication.databinding.FragmentBookmarkBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel
import com.maroqi.newsapplication.presentation.views.adapters.BookmarkListAdapter

class BookmarkFragment : Fragment() {
    private var binding: FragmentBookmarkBinding? = null

    private val viewModel: MainViewModel by activityViewModels { NewsViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater)

        initList()

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getBookmarks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) {
            binding = null
        }
    }

    private fun initList() {
        if (binding != null) {
            binding!!.rvNews.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = BookmarkListAdapter(listOf())
            }

            viewModel.bookmarks.observe(viewLifecycleOwner) {
                binding!!.rvNews.adapter = BookmarkListAdapter(it)
            }
        }
    }
}
