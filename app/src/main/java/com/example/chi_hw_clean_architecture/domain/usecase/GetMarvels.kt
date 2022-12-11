package com.example.chi_hw_clean_architecture.domain.usecase

import com.example.chi_hw_clean_architecture.data.repository.MarvelDataRepository

class GetMarvels(private val repository: MarvelDataRepository) {

    suspend fun execute() = repository.fetchData()

}