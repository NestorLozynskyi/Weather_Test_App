package com.zero.weatherapptest.ui.screens.profile

import androidx.lifecycle.MutableLiveData
import com.zero.weatherapptest.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val repository: ProfileRepository,
): BaseViewModel() {

    val ldCity = MutableLiveData<Any?>()
    private var getCityJob: Job? = null

    fun getProfile() {
         getCityJob = launch(::onErrorHandler) {
             withContext(Dispatchers.Main){/*onStartProgress.value = Unit*/}
             ldCity.postValue(repository.getCity())
             withContext(Dispatchers.Main){/*onEndProgress.value = Unit*/}
         }
    }

    override fun onErrorHandler(throwable: Throwable) {
        showError.postValue("Error")
    }
}