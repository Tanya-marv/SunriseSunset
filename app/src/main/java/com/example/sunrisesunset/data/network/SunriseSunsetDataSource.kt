package com.example.sunrisesunset.data.network

import com.example.sunrisesunset.data.network.model.DayInfo

interface SunriseSunsetDataSource {

    suspend fun fetchSunDetailsByLocation(latitude: Double, longitude: Double): DayInfo
}