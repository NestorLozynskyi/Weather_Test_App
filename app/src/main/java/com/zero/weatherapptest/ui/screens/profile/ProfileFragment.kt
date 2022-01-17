package com.zero.weatherapptest.ui.screens.profile

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.base.baseExtensions.observeLiveData
import com.zero.weatherapptest.data.objects.LangData
import com.zero.weatherapptest.databinding.ScreenProfileBinding
import com.zero.weatherapptest.ui.screens.generals.generalsAdapters.CityAdapter
import com.zero.weatherapptest.utils.extensions.gone
import com.zero.weatherapptest.utils.extensions.visible
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : BaseFragment<ScreenProfileBinding>() {

    override val viewModel: ProfileViewModel by viewModel()

    lateinit var cityAdapter: CityAdapter
    lateinit var langAdapter: LangAdapter

    override fun onCreate(i: LayoutInflater, c: ViewGroup?) = ScreenProfileBinding.inflate(i, c, false)

    override fun initialize() {
        with(binding){
            search.setOnClickListener {
                rvGone()
                binding.etEnterCity.text?.toString()?.let { it1 -> viewModel.getCity(it1) }
            }
            etEnterCity.setOnClickListener {
                rvGone()
            }

            themeWhite.setOnClickListener {
                rvGone()
                viewModel.saveTheme(false)
            }
            themeBlack.setOnClickListener {
                rvGone()
                viewModel.saveTheme(true)
            }

            /** Adapters */

            cityAdapter = CityAdapter {
                rvGone()
                viewModel.saveCity(it)
            }
            rvCity.adapter = cityAdapter

            langAdapter = LangAdapter {
                rvGone()
                viewModel.saveLang(it.id)
                val locale = Locale(it.pref)
                Locale.setDefault(locale)
                val configuration = Configuration()
                configuration.locale = locale
                requireContext().resources.updateConfiguration(configuration, null)
            }
            rvLanguage.adapter = langAdapter

            /** Language */
            language.text = when (viewModel.getLang()) {
                1 -> getString(R.string.lang_en_native)
                2 -> getString(R.string.lang_ua_native)
                3 -> getString(R.string.lang_pl_native)
                4 -> getString(R.string.lang_ru_native)
                else -> getString(R.string.lang_def)
            }
            language.setOnClickListener {
                rvLanguage.visible()
                val langList: ArrayList<LangData>
                with(resources) {
                    langList = arrayListOf(
                        LangData(0, getString(R.string.lang_def), getString(R.string.lang)),
                        LangData(1, getString(R.string.lang_en_native), "en"),
                        LangData(2, getString(R.string.lang_ua_native), "uk"),
                        LangData(3, getString(R.string.lang_pl_native), "pl"),
                        LangData(4, getString(R.string.lang_ru_native), "ru")
                    )
                }
                langAdapter.setData(langList)
            }

            /** bottom bar */
            with(bottomBar) {
                buttonSearchFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_SearchFragment)
                }
                buttonStatisticFragment.setOnClickListener {
                    navController?.navigate(R.id.action_to_StatisticFragment)
                }
            }
        }
    }

    private fun rvGone(){
        binding.rvCity.gone()
        binding.rvLanguage.gone()
    }

    override fun observe() {
        observeLiveData(viewModel.ldCity){
            if (it != null) {
                binding.rvCity.visible()
                cityAdapter.setData(it.results)
            }
        }
    }
}