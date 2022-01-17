package com.zero.weatherapptest.ui.screens.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.base.baseExtensions.observeLiveData
import com.zero.weatherapptest.databinding.ScreenStatisticBinding
import com.zero.weatherapptest.ui.screens.generals.generalsAdapters.CityAdapter
import com.zero.weatherapptest.utils.extensions.visible
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticFragment: BaseFragment<ScreenStatisticBinding>() {
    override val viewModel: StatisticViewModel by viewModel()

    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(i: LayoutInflater, c: ViewGroup?) = ScreenStatisticBinding.inflate(i,c, false)

    override fun initialize() {

        with(binding){

            cityAdapter = CityAdapter {}
            rvCity.adapter = cityAdapter

            /** bottom bar */
            with(bottomBar) {
                buttonProfileFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_ProfileFragment_fromRight)
                }
                buttonSearchFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_SearchFragment)
                }
            }
        }
    }

    override fun observe() {
        observeLiveData(viewModel.ldHistory){
            println("$it")
            if (it != null) {
                binding.rvCity.visible()
                cityAdapter.setData(it)
            }
        }
    }
}