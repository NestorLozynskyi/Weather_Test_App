package com.zero.weatherapptest.utils.shared

import android.content.SharedPreferences
import com.google.gson.Gson
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.utils.shared.PrefHelper.set

class SharedManager(private val preferences: SharedPreferences, private val gson: Gson) {

    companion object {
        private const val USER_CITY = "USER_CITY"
        private const val USER_THEME = "USER_THEME"
        private const val USER_LANG = "USER_LANG"
    }

    var userCity: CityData?
        get() {
            val json = preferences.getString(USER_CITY, "")
            return if (json == "") null
            else gson.fromJson(json, CityData::class.java)

        }
        set(value) {
            preferences[USER_CITY] = gson.toJson(value)
        }

    var userTheme: Boolean
        get() = preferences.getBoolean(USER_THEME, true)
        set(value) { preferences[USER_THEME] = value }

    var userLang: Int
        get() = preferences.getInt(USER_LANG, 0)
        set(value) { preferences[USER_LANG] = value }
}