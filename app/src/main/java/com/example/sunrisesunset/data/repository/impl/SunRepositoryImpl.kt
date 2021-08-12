package com.example.sunrisesunset.data.repository.impl

import com.example.sunrisesunset.data.network.SunriseSunsetDataSource
import com.example.sunrisesunset.data.network.model.DayInfo
import com.example.sunrisesunset.data.repository.SunRepository

class SunRepositoryImpl(
    private val dataSource: SunriseSunsetDataSource
) : SunRepository{

    override suspend fun retrieveSunDetailsByLocation(latitude: Double, longitude: Double): DayInfo{
        return dataSource.fetchSunDetailsByLocation(latitude, longitude)
    }
}