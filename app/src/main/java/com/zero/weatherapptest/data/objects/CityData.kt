package com.zero.weatherapptest.data.objects

import com.google.gson.annotations.SerializedName

data class CityResponse(
    val results: ArrayList<CityData>
)
data class CityData(
    //val bounds: BoundsCR,
    val components: ComponentsCR,
    val geometry: CoordinatesCR
)
data class BoundsCR(
    val northeast: CoordinatesCR,
    val southwest: CoordinatesCR
)
data class CoordinatesCR(
    val lat: Float,
    val lng: Float
)
data class ComponentsCR(
    val city: String? = null,
    val village: String? = null,
    val state: String = "",
    @SerializedName("country_code")
    val countryCode: String = ""
)
