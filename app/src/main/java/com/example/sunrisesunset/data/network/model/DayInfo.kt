package com.example.sunrisesunset.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayInfo(
    @Json(name = "results") val results: Result
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "sunrise") val sunrise: String,
        @Json(name = "sunset") val sunset: String,
        @Json(name = "solar_noon") val solarNoon: String,
        @Json(name = "day_length") val dayLength: String,
        @Json(name = "civil_twilight_begin") val civilTwilightBegin: String,
        @Json(name = "civil_twilight_end") val civilTwilightEnd: String,
        @Json(name = "nautical_twilight_begin") val nauticalTwilightBegin: String,
        @Json(name = "nautical_twilight_end") val nauticalTwilightEnd: String,
        @Json(name = "astronomical_twilight_begin") val astronomicalTwilightBegin: String,
        @Json(name = "astronomical_twilight_end") val astronomicalTwilightEnd: String
        )
}