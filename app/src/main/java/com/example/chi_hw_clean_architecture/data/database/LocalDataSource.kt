package com.example.chi_hw_clean_architecture.data.database

import com.example.chi_hw_clean_architecture.data.MarvelDataSource
import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.di.Injection
import kotlinx.coroutines.flow.Flow

class LocalDataSource : MarvelDataSource {

    companion object {
        private var INSTANCE: MarvelDataSource? = null
        fun getInstance(): MarvelDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource()
            }
            return INSTANCE!!
        }
    }

    override suspend fun fetchData(): Flow<List<Marvel>> = Injection.provideMarvelDao().fetchMarvels()

    override suspend fun saveData(list: List<Marvel>) = Injection.provideMarvelDao().addMarvels(list)
}