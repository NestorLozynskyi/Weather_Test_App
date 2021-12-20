package com.zero.testappweather.di.koin

import com.google.gson.GsonBuilder
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

    /*factory { PreferenceHelper.customPrefs(get(), "wta") }

    factory { SharedManager(get(), get()) }*/
}