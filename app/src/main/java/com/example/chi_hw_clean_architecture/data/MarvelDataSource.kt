package com.example.chi_hw_clean_architecture.data

import com.example.chi_hw_clean_architecture.data.model.Marvel
import kotlinx.coroutines.flow.Flow

interface MarvelDataSource {
    suspend fun fetchData(): Flow<List<Marvel>>
    suspend fun saveData(list: List<Marvel>)
}