package com.zero.weatherapptest.api

import retrofit2.http.GET

interface CityApiInterface {
    @GET("/api/account")
    suspend fun getCity(): Any
}