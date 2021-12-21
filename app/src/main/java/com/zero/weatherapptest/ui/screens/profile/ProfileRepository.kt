package com.zero.weatherapptest.ui.screens.profile

import com.zero.weatherapptest.api.CityApiInterface

class ProfileRepository(private val api: CityApiInterface) {
    suspend fun getCity() = api.getCity()
}