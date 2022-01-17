package com.zero.weatherapptest.ui.screens.statistics

import androidx.lifecycle.MutableLiveData
import com.zero.weatherapptest.base.BaseViewModel
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.data.objects.ComponentsCR
import com.zero.weatherapptest.data.objects.CoordinatesCR
import kotlinx.coroutines.Job

class StatisticViewModel(
    private val historyDao: HistoryDao
): BaseViewModel() {

    val ldHistory = MutableLiveData<ArrayList<CityData>>()

    private var getHistoryJob: Job? = null

    init {
        getHistoryJob = launch(::onErrorHandler) {
            val historyData = historyDao.all
            val cityDataList: ArrayList<CityData> = ArrayList()
            for (i in historyData){
                if (i.lat != null && i.lng != null) {
                    cityDataList.add(
                        CityData(
                            ComponentsCR(
                                if (i.isCity) i.cityName else null,
                                if (!i.isCity) i.cityName else null,
                                i.region,
                                i.countryCode
                            ),
                            CoordinatesCR(
                                i.lat!!,
                                i.lng!!
                            )
                        )
                    )
                }
            }
            ldHistory.postValue(cityDataList)
        }
    }
}