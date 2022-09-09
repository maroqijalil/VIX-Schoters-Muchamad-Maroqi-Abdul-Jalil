package com.maroqi.newsapplication.application.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maroqi.newsapplication.application.usecases.UseCases

object NewsViewModelFactory : ViewModelProvider.Factory {
    lateinit var useCases: UseCases

    fun inject(useCases: UseCases) {
        this.useCases = useCases
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UseCases::class.java).newInstance(useCases)
    }
}
