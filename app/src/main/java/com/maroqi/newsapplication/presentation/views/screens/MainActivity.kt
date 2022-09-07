package com.maroqi.newsapplication.presentation.views.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.maroqi.newsapplication.R
import com.maroqi.newsapplication.databinding.ActivityMainBinding
import com.maroqi.newsapplication.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appConfiguration)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getNews()
    }
}
