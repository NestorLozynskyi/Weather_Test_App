package com.zero.weatherapptest.ui.screens.search

import androidx.lifecycle.MutableLiveData
import com.zero.weatherapptest.base.BaseViewModel
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.data.objects.CityResponse
import com.zero.weatherapptest.data.objects.History
import com.zero.weatherapptest.data.objects.WeatherResponse
import com.zero.weatherapptest.ui.screens.generals.generalRepository.GeneralRepository
import kotlinx.coroutines.Job

class SearchViewModel(
    private val repository: GeneralRepository,
    //private val sharedManager: SharedManager,
    private val historyDao: HistoryDao
) : BaseViewModel() {

    val ldCity = MutableLiveData<CityResponse?>()
    val ldWeather = MutableLiveData<WeatherResponse?>()

    private var getCityJob: Job? = null
    private var getWeatherJob: Job? = null
    private var addHistoryJob: Job? = null

    fun getCity(city: String) {
        getCityJob = launch(::onErrorHandler) {
            //withContext(Dispatchers.Main){onProgress.value = True}
            ldCity.postValue(repository.getCity(city))

            //withContext(Dispatchers.Main){onProgress.value = False}
        }
    }
    fun getWeather(cityData: CityData) {
        getWeatherJob = launch(::onErrorHandler) {
            saveCity(cityData)
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
            /*history.cityName = cityData.components.city ?: cityData.components.village ?: ""
            history.isCity = cityData.components.village.isNullOrBlank()
            history.region = cityData.components.state
            history.lat = cityData.geometry.lat
            history.lng = cityData.geometry.lng*/
            historyDao.insert(history)
        }
    }
}