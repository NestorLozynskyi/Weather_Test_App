package com.zero.weatherapptest.data.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_history")
class History {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var cityName: String = ""
    var isCity: Boolean = true
    var region: String = ""
    var countryCode: String = ""
    var lat: Float? = null
    var lng: Float? = null
}