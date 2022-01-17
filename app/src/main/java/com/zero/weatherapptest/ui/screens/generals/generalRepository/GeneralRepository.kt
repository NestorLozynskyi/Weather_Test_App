package com.zero.weatherapptest.ui.screens.generals.generalRepository

import com.zero.weatherapptest.api.CityApiInterface
import com.zero.weatherapptest.api.WeatherApiInterface

class GeneralRepository(private val api1: CityApiInterface, private val api2: WeatherApiInterface) {
    suspend fun getCity(city : String) = api1.getCity(city)
    suspend fun getWeather(lat: Float, lon: Float, lang: String) = api2.getWeather(lat, lon, lang)
}