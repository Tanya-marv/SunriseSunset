package com.example.sunrisesunset.di

import com.example.sunrisesunset.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(repository = get())  }
}