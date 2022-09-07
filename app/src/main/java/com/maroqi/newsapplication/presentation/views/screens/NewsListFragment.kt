package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.maroqi.newsapplication.databinding.FragmentNewsListBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel
import com.maroqi.newsapplication.presentation.views.adapters.NewsListAdapter

class NewsListFragment : Fragment() {
    private var binding: FragmentNewsListBinding? = null

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater)

        initList()

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) {
            binding = null
        }
    }

    private fun initList() {
        if (binding != null) {
            newsAdapter = NewsListAdapter(listOf())

            binding!!.rvNews.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter
            }

            viewModel.news.observe(viewLifecycleOwner) {
                binding!!.rvNews.adapter = NewsListAdapter(it)
            }
        }
    }
}
