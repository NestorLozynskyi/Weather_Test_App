package com.zero.weatherapptest.ui.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.base.baseExtensions.observeLiveData
import com.zero.weatherapptest.databinding.ScreenSearchBinding
import com.zero.weatherapptest.ui.screens.generals.generalsAdapters.CityAdapter
import com.zero.weatherapptest.utils.extensions.gone
import com.zero.weatherapptest.utils.extensions.visible
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment() : BaseFragment<ScreenSearchBinding>() {

    override val viewModel: SearchViewModel by viewModel()

    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(i: LayoutInflater, c: ViewGroup?) = ScreenSearchBinding.inflate(i, c, false)

    override fun initialize() {

        with(binding){
            search.setOnClickListener {
                rvGone()
                binding.etEnterCity.text?.toString()?.let { it1 -> viewModel.getCity(it1) }
            }

            cityAdapter = CityAdapter {
                viewWeatherInfo.tvCityName.text =
                    when {
                        !it.components.city.isNullOrBlank() -> it.components.city
                        !it.components.village.isNullOrBlank() -> it.components.village
                        else -> it.components.state
                    }

                viewModel.getWeather(it)
                rvGone()
            }
            rvCity.adapter = cityAdapter

            /** bottom bar */
            with(bottomBar) {
                buttonProfileFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_ProfileFragment_fromLeft)
                }
                buttonStatisticFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_StatisticFragment)
                }
            }
        }
    }

    private fun rvGone(){
        binding.rvCity.gone()
    }

    override fun observe() {
        with(viewModel){
            observeLiveData(ldCity){
                if (it != null) {
                    binding.rvCity.visible()
                    cityAdapter.setData(it.results)
                }
            }
            observeLiveData(ldWeather){ response ->
                if (response != null){
                    val noDataMessage: String = resources.getString(R.string.no_data)
                    val absentMessage: String = resources.getString(R.string.absent)
                    with(binding){
                        with(viewWeatherInfo) {
                            this.root.visible()
                            tvTemperatureValue.text = (response.current.temp ?: noDataMessage).toString()
                            tvPressureValue.text = (response.current.pressure ?: noDataMessage).toString()
                            tvHumidityValue.text = (response.current.humidity ?: noDataMessage).toString()
                            tvWindValue.text = (response.current.windSpeed ?: noDataMessage).toString()
                            tvRainValue.text = (response.current.rain?.h1 ?: response.current.snow?.h1 ?: absentMessage).toString()
                        }
                    }
                }
            }
            observeLiveData(ldSetCity){
                binding.viewWeatherInfo.tvCityName.text = it
            }
        }
    }
}