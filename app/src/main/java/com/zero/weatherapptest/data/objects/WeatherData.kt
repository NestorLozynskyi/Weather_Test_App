package com.zero.weatherapptest.data.objects

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current: CurrentWR/*,
    val hourly: ArrayList<CurrentWR>,
    val daily: ArrayList<CurrentWR>*/
)

data class CurrentWR(
    val temp: Float? = null,                // default: kelvin, metric: Celsius, imperial: Fahrenheit
    @SerializedName("feels_like")
    val feelsLike: Float? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    @SerializedName("dew_point")
    val dewPoint: Float? = null,            // Atmospheric temperature (varying according to pressure and humidity)
    val uvi: Int? = null,                   // ультрафіолет (0-2: ок, 3-7: обмежено 8<: небезпечно)
    val clouds: Float? = null,
    val visibility: Float? = null,            // Average visibility, metres
    @SerializedName("wind_speed")
    val windSpeed: Int? = null,
    @SerializedName("wind_deg")
    val windDeg: Float? = null,               // degrees (meteorological)
    @SerializedName("wind_gust")
    val windGust: Float? = null,            // default: metre/sec, metric: metre/sec, imperial: miles/hour.
    val rain: RainOrSnowWR? = null,                 //todo need test
    val snow: RainOrSnowWR? = null,                 //todo need test
    val weather: ArrayList<WeatherWR>? = null
)

data class WeatherWR(
    val id: Float? = null,
    val main: String? = null,
    val description: String? = null                 // translated
)

data class RainOrSnowWR(
    val h1: Float? = null
)