package com.zero.weatherapptest.api

import com.zero.weatherapptest.data.constants.Constants
import com.zero.weatherapptest.data.objects.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiInterface {
    @GET("/geocode/v1/json")
    suspend fun getCity(
        @Query("q") q: String,
        @Query("key") key: String = Constants.CITY_TOKEN,
    ): CityResponse?
}