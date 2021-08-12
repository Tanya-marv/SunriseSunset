package com.example.sunrisesunset.data.repository

import com.example.sunrisesunset.data.network.model.DayInfo

interface SunRepository {

    suspend fun retrieveSunDetailsByLocation(latitude: Double, longitude: Double): DayInfo
}