package com.maroqi.newsapplication.presentation.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maroqi.newsapplication.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding: FragmentNewsListBinding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (_binding != null) {
            _binding = null
        }
    }
}
