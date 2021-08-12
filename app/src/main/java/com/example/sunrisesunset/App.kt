package com.example.sunrisesunset

import android.app.Application
import com.example.sunrisesunset.di.networkModule
import com.example.sunrisesunset.di.repositoryModule
import com.example.sunrisesunset.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(networkModule + repositoryModule + viewModelModule )
        }
    }
}