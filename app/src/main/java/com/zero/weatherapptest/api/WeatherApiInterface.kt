package com.zero.weatherapptest.api

import com.zero.weatherapptest.data.constants.Constants
import com.zero.weatherapptest.data.objects.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    /*@GET(Constants.WEATHER_URL+"/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("lang") lang: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.WEATHER_TOKEN
    ): Any?*/

    @GET(Constants.WEATHER_URL+"/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("lang") lang: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.WEATHER_TOKEN
    ): WeatherResponse?
}