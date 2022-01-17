package com.zero.weatherapptest.ui.screens.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zero.weatherapptest.base.BaseViewModel
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.data.objects.CityResponse
import com.zero.weatherapptest.utils.shared.SharedManager
import kotlinx.coroutines.Job
import com.zero.weatherapptest.ui.screens.generals.generalRepository.GeneralRepository


class ProfileViewModel(
    private val repository: GeneralRepository,
    private val sharedManager: SharedManager,
    val context: Context
): BaseViewModel() {

    val ldCity = MutableLiveData<CityResponse?>()

    private var getCityJob: Job? = null

    fun getCity(city: String) {
         getCityJob = launch(::onErrorHandler) {
             ldCity.postValue(repository.getCity(city))
         }
    }

    fun saveCity(city: CityData){
        sharedManager.userCity = city
    }
    fun saveTheme(whiteTheme: Boolean){
        sharedManager.userTheme = whiteTheme
    }
    fun saveLang(langId: Int){
        sharedManager.userLang = langId
    }
    fun getLang(): Int = sharedManager.userLang

    override fun onErrorHandler(throwable: Throwable) {
        showError.postValue("Error")
    }
}