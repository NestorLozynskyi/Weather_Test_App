package com.zero.weatherapptest.di.koin

import com.google.gson.GsonBuilder
import com.zero.weatherapptest.ui.screens.profile.ProfileNavigation
import com.zero.weatherapptest.ui.screens.search.SearchNavigation
import com.zero.weatherapptest.ui.screens.statistics.StatisticNavigation
import com.zero.weatherapptest.utils.shared.PrefHelper
import com.zero.weatherapptest.utils.shared.SharedManager
import org.koin.dsl.module

val viewModelModule = module {

    //viewModel { MainViewModel() }
}

val networkModule = module {

    fun provideGson() = GsonBuilder()
        .setLenient()
        .serializeSpecialFloatingPointValues()
        .serializeNulls().create()

    single { provideGson() }
}

val sharedPrefModule = module {

    factory { PrefHelper.customPrefs(get(), "wta") }

    factory { SharedManager(get(), get()) }
}

val navigationModule = module {
    factory { ProfileNavigation() }
    factory { SearchNavigation() }
    factory { StatisticNavigation() }
}