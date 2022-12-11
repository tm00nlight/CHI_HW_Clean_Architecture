package com.example.chi_hw_clean_architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chi_hw_clean_architecture.di.Injection
import com.example.chi_hw_clean_architecture.presentation.screens.list.MarvelListViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MarvelListViewModel::class.java) {
            return MarvelListViewModel(Injection.provideUseCaseHandler(),
                Injection.provideGetMarvels(), Injection.provideSaveMarvels()) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory()
            }
            return INSTANCE!!
        }
    }
}