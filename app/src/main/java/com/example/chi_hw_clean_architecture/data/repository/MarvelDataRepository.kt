package com.example.chi_hw_clean_architecture.data.repository

import com.example.chi_hw_clean_architecture.data.MarvelDataSource
import com.example.chi_hw_clean_architecture.data.model.Marvel
import kotlinx.coroutines.flow.Flow

class MarvelDataRepository(private val localDataSource: MarvelDataSource, private val remoteDataSource: MarvelDataSource) {
    suspend fun fetchData(): Flow<List<Marvel>> = remoteDataSource.fetchData()
    suspend fun saveData(list: List<Marvel>) = localDataSource.saveData(list)

    companion object {
        private var INSTANCE: MarvelDataRepository? = null
        fun getInstance(localDataSource: MarvelDataSource, remoteDataSource: MarvelDataSource): MarvelDataRepository {
            if (INSTANCE == null) {
                INSTANCE = MarvelDataRepository(localDataSource, remoteDataSource)
            }
            return INSTANCE!!
        }
    }
}