package com.example.chi_hw_clean_architecture.presentation.screens.list

import com.example.chi_hw_clean_architecture.presentation.model.MarvelPresentationModel

interface AdapterCallback {
    fun updateUI(list: List<MarvelPresentationModel>)
}