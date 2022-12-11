package com.example.chi_hw_clean_architecture.di

import com.example.chi_hw_clean_architecture.data.MarvelDataSource
import com.example.chi_hw_clean_architecture.data.database.LocalDataSource
import com.example.chi_hw_clean_architecture.data.database.MarvelDataBase
import com.example.chi_hw_clean_architecture.data.network.BASE_URL_MARVEL
import com.example.chi_hw_clean_architecture.data.network.MarvelApi
import com.example.chi_hw_clean_architecture.data.network.RemoteDataSource
import com.example.chi_hw_clean_architecture.data.network.RetrofitClient
import com.example.chi_hw_clean_architecture.data.repository.MarvelDataRepository
import com.example.chi_hw_clean_architecture.domain.usecase.GetMarvels
import com.example.chi_hw_clean_architecture.domain.usecase.SaveMarvels
import com.example.chi_hw_clean_architecture.domain.usecase.UseCaseHandler
import com.example.chi_hw_clean_architecture.presentation.ViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {
    private lateinit var dataBase: MarvelDataBase

    fun initInjection(dataBase: MarvelDataBase) {
        this.dataBase = dataBase
    }

    fun provideMarvelDataRepository(): MarvelDataRepository {
        return MarvelDataRepository.getInstance(provideLocalDataSource(), provideRemoteDataSource())
    }
    fun provideViewModelFactory() = ViewModelFactory.getInstance()
    fun provideLocalDataSource(): MarvelDataSource = LocalDataSource.getInstance()
    fun provideRemoteDataSource(): MarvelDataSource = RemoteDataSource.getInstance()
    fun provideGetMarvels() = GetMarvels(provideMarvelDataRepository())
    fun provideSaveMarvels() = SaveMarvels(provideMarvelDataRepository())
    fun provideUseCaseHandler() = UseCaseHandler.getInstance()

//    fun provideRetrofit() = RetrofitClient.getClient()
//    fun provideMarvelApi() = retrofit.create(MarvelApi::class.java)
    fun provideMarvelDao() = dataBase.marvelDao

    fun provideMarvelApi(): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MARVEL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
}