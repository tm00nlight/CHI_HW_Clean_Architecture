package com.example.chi_hw_clean_architecture.data.network

import com.example.chi_hw_clean_architecture.data.model.ResponseDataModel
import retrofit2.Call
import retrofit2.http.GET

interface MarvelApi {
    @GET(".")
    suspend fun getMarvels(): Call<List<ResponseDataModel>>
}