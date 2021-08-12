package com.example.sunrisesunset.data.network.impl

import com.example.sunrisesunset.data.network.SunriseSunsetDataSource
import com.example.sunrisesunset.data.network.api.SunriseSunsetApi
import com.example.sunrisesunset.data.network.model.DayInfo

class SunriseSunsetDataSourceImpl(
    private val api: SunriseSunsetApi
) : SunriseSunsetDataSource {

    override suspend fun fetchSunDetailsByLocation(latitude: Double, longitude: Double): DayInfo {
        return api.fetchSunDetailsByLocation(latitude, longitude)
    }
}