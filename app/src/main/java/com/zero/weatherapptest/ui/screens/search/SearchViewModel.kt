package com.zero.weatherapptest.ui.screens.search

import androidx.lifecycle.MutableLiveData
import com.zero.weatherapptest.base.BaseViewModel
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.data.objects.CityResponse
import com.zero.weatherapptest.data.objects.History
import com.zero.weatherapptest.data.objects.WeatherResponse
import com.zero.weatherapptest.ui.screens.generals.generalRepository.GeneralRepository
import com.zero.weatherapptest.utils.shared.SharedManager
import kotlinx.coroutines.Job

class SearchViewModel(
    private val repository: GeneralRepository,
    private val historyDao: HistoryDao,
    private val sharedManager: SharedManager
) : BaseViewModel() {

    val ldCity = MutableLiveData<CityResponse?>()
    val ldWeather = MutableLiveData<WeatherResponse?>()
    val ldSetCity = MutableLiveData<String>()

    private var getCityJob: Job? = null
    private var getWeatherJob: Job? = null
    private var addHistoryJob: Job? = null

    init {
        val userCity = sharedManager.userCity
        if (userCity != null) {
            getWeather(userCity, false)
            ldSetCity.postValue(
                when {
                    !userCity.components.city.isNullOrBlank() -> userCity.components.city
                    !userCity.components.village.isNullOrBlank() -> userCity.components.village
                    else -> userCity.components.state
                }
            )
        }
    }

    fun getCity(city: String) {
        getCityJob = launch(::onErrorHandler) {
            //withContext(Dispatchers.Main){onProgress.value = True}
            ldCity.postValue(repository.getCity(city))
            //withContext(Dispatchers.Main){onProgress.value = False}
        }
    }
    fun getWeather(cityData: CityData, save: Boolean = true) {
        getWeatherJob = launch(::onErrorHandler) {
            if (save) saveCity(cityData)
            ldWeather.postValue(repository.getWeather(cityData.geometry.lat, cityData.geometry.lng, "ua"))
        }
    }

    private fun saveCity(cityData: CityData){
        addHistoryJob = launch(::onErrorHandler) {
            val history = History().apply {
                with(cityData) {
                    cityName = components.city ?: cityData.components.village ?: ""
                    isCity = components.village.isNullOrBlank()
                    region = components.state
                    countryCode = components.countryCode
                    lat = geometry.lat
                    lng = geometry.lng
                }
            }
            historyDao.insert(history)
        }
    }
}