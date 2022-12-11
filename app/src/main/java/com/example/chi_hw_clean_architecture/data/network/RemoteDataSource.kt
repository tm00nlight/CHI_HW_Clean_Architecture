package com.example.chi_hw_clean_architecture.data.network

import com.example.chi_hw_clean_architecture.data.MarvelDataSource
import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.data.model.ResponseDataModel
import com.example.chi_hw_clean_architecture.di.Injection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RemoteDataSource : MarvelDataSource {

    companion object {
        private var INSTANCE: MarvelDataSource? = null
        fun getInstance(): MarvelDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource()
            }
            return INSTANCE!!
        }
    }

    override suspend fun fetchData(): Flow<List<Marvel>> {
        val result = Injection.provideMarvelApi().getMarvels().execute()
        if (result.isSuccessful) {
            return flow { result.body()!!.map { m -> m.toDbModel() } }
        }

        return Injection.provideLocalDataSource().fetchData()
    }

    override suspend fun saveData(list: List<Marvel>) {
        TODO("Not yet implemented")
    }
}