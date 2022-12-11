package com.example.chi_hw_clean_architecture.domain.usecase

import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.data.repository.MarvelDataRepository

class SaveMarvels(private val repository: MarvelDataRepository) {

    suspend fun execute(list: List<Marvel>) = repository.saveData(list)
}