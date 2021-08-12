package com.example.sunrisesunset.di

import com.example.sunrisesunset.BuildConfig
import com.example.sunrisesunset.data.network.SunriseSunsetDataSource
import com.example.sunrisesunset.data.network.api.SunriseSunsetApi
import com.example.sunrisesunset.data.network.impl.SunriseSunsetDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single(named("BaseUrl")) { BuildConfig.SUNRISE_SUNSET_BASE_URL }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(get<String>(named("BaseUrl")))
            .build()
            .create(SunriseSunsetApi::class.java)
    }

    single<SunriseSunsetDataSource> {
        SunriseSunsetDataSourceImpl(api = get())
    }
}