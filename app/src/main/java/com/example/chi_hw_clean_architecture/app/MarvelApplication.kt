package com.example.chi_hw_clean_architecture.app

import android.app.Application
import com.example.chi_hw_clean_architecture.data.database.MarvelDataBase
import com.example.chi_hw_clean_architecture.di.Injection

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Injection.initInjection(MarvelDataBase.getDataBase(applicationContext))
    }
}