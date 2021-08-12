package com.example.sunrisesunset.di

import com.example.sunrisesunset.data.repository.SunRepository
import com.example.sunrisesunset.data.repository.impl.SunRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<SunRepository> { SunRepositoryImpl(dataSource = get())}
}
