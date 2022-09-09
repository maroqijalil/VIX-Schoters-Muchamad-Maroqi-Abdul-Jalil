package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maroqi.newsapplication.R
import com.maroqi.newsapplication.databinding.FragmentNewsListBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel
import com.maroqi.newsapplication.presentation.views.adapters.NewsListAdapter

class NewsListFragment : Fragment() {
    private var binding: FragmentNewsListBinding? = null

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
                    else -> true
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
                adapter = NewsListAdapter(listOf())
            }

            viewModel.news.observe(viewLifecycleOwner) {
                binding!!.rvNews.adapter = NewsListAdapter(it) { item ->
                    val action = NewsListFragmentDirections.toDetail(item)
                    findNavController().navigate(action)
                }
            }
        }
    }
}
