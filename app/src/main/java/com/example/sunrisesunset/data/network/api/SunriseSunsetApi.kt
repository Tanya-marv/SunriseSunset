package com.example.sunrisesunset.data.network.api

import com.example.sunrisesunset.data.network.model.DayInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseSunsetApi {

    // ""json?lat={latitude}lng={longitude}&date=today""
    @GET("json")
    suspend fun fetchSunDetailsByLocation(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double
    ): DayInfo
}