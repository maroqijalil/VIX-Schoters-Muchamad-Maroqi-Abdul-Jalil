package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

    override fun onResume() {
        super.onResume()
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
            val bookmarkListAdapter = BookmarkListAdapter(
                listOf(),
                { item ->
                    val action = NewsListFragmentDirections.toDetail(item)
                    findNavController().navigate(action)
                },
                { viewModel.bookmark(it) }
            )

            binding!!.rvNews.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = bookmarkListAdapter
            }

            viewModel.bookmarks.observe(viewLifecycleOwner) {
                bookmarkListAdapter.changeList(it)
            }
        }
    }
}
