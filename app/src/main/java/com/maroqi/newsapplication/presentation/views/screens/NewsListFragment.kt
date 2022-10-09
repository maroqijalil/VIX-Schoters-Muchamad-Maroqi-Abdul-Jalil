package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maroqi.newsapplication.R
import com.maroqi.newsapplication.application.factory.NewsViewModelFactory
import com.maroqi.newsapplication.databinding.FragmentNewsListBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel
import com.maroqi.newsapplication.presentation.views.adapters.NewsListAdapter

class NewsListFragment : Fragment() {
    private var binding: FragmentNewsListBinding? = null

//    private val viewModel: MainViewModel by activityViewModels { NewsViewModelFactory }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater)

        initList()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.profile -> {
                        val action = NewsListFragmentDirections.toProfile()
                        findNavController().navigate(action)
                        true
                    }
                    R.id.bookmark -> {
                        val action = NewsListFragmentDirections.toBookmark()
                        findNavController().navigate(action)
                        true
                    }
                    R.id.search -> {
                        (menuItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(text: String?): Boolean {
                                if (!text.isNullOrEmpty()) {
                                    viewModel.getNews(text)
                                }

                                return false
                            }

                            override fun onQueryTextChange(text: String?): Boolean = true
                        })
                        true
                    }
                    else -> true
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) {
            binding = null
        }
    }

    private fun initList() {
        if (binding != null) {
            val newsListAdapter = NewsListAdapter(
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
                adapter = newsListAdapter
            }

            viewModel.news.observe(viewLifecycleOwner) {
                newsListAdapter.changeList(it)
            }
        }
    }
}
