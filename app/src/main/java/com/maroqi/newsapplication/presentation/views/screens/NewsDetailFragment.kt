package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.maroqi.newsapplication.R
import com.maroqi.newsapplication.databinding.FragmentNewsDetailBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel

class NewsDetailFragment : Fragment() {
    private var binding: FragmentNewsDetailBinding? = null

    private val arguments: NewsDetailFragmentArgs by navArgs()

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater)

        binding!!.let {
            val news = arguments.news
            Glide.with(it.root)
                .load(news.image)
                .into(it.ivNews)

            it.tvNewsTitle.text = news.title
            it.tvNewsAuthor.text = news.author
            it.tvNewsDate.text = news.getDate()
            it.tvNewsContent.text = news.content
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding!!.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) {
            binding = null
        }
    }
}
