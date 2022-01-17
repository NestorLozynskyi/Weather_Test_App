package com.zero.weatherapptest.di.koin

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zero.weatherapptest.api.CityApiInterface
import com.zero.weatherapptest.ui.screens.generals.generalRepository.GeneralRepository
import com.zero.weatherapptest.ui.screens.search.SearchViewModel
import com.zero.weatherapptest.utils.shared.PrefHelper
import com.zero.weatherapptest.utils.shared.SharedManager
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import com.readystatesoftware.chuck.ChuckInterceptor
import com.zero.weatherapptest.api.WeatherApiInterface
import com.zero.weatherapptest.base.BaseDB
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.network.interceptors.ParamsInterceptor
import com.zero.weatherapptest.network.interceptors.ResponseInterceptor
import com.zero.weatherapptest.network.retrofit.RetrofitFactory
import com.zero.weatherapptest.network.retrofit.RetrofitFactoryImplement
import com.zero.weatherapptest.ui.screens.profile.ProfileViewModel
import com.zero.weatherapptest.ui.screens.statistics.StatisticViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext

val retrofitModule = module(override = true) {

    single { ChuckInterceptor(get()) }

    single { ParamsInterceptor() }
    single { ResponseInterceptor() }
    single<Gson> { GsonBuilder().setLenient().create() }
    single<RetrofitFactory> { RetrofitFactoryImplement(get(), get(), get()) }
    single { get<RetrofitFactory>().createRetrofit(get()) }

    single { get<Retrofit>().create(CityApiInterface::class.java) }
    single { get<Retrofit>().create(WeatherApiInterface::class.java) }
}

val viewModelModule = module {
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { StatisticViewModel(get()) }
}

/*val navigationModule = module {
   // factory { ProfileNavigation() }
    factory { SearchNavigation() }
    factory { StatisticNavigation() }
}*/

val repositoryModule = module {
    factory { GeneralRepository(get(), get()) }
}

/*val networkModule = module {

    fun provideGson() = GsonBuilder()
        .setLenient()
        .serializeSpecialFloatingPointValues()
        .serializeNulls().create()

    single { provideGson() }
}*/

val sharedPrefModule = module {

    factory { PrefHelper.customPrefs(get(), "Weather_test_DevNestor_") }

    factory { SharedManager(get(), get()) }
}

val dataBaseModule = module {
    //single { BaseDB.getInstance(androidContext()).historyDao }
    fun provideDataBase(context: Context): BaseDB {
        return Room.databaseBuilder(context, BaseDB::class.java, "database")
            .fallbackToDestructiveMigration()
            .setQueryExecutor{ }
            .build()
    }

    fun provideDao(dataBase: BaseDB): HistoryDao? {
        return dataBase.historyDao()
    }

    single { provideDataBase(get()) }
    single { provideDao(get()) }
}
    /*fun provideDataBase(context: Context): BaseDB {
        return Room.databaseBuilder(context, BaseDB::class.java, "database")
            .fallbackToDestructiveMigration()
            .setQueryExecutor{ }
            .build()
    }

    fun provideDao(dataBase: BaseDB): HistoryDao? {
        return dataBase.historyDao()
    }

    single { provideDataBase(get()) }
    single { provideDao(get()) }*/



    /*fun db(context: Context): BaseDB = Room.databaseBuilder(
        context,
        BaseDB::class.java, "database"
    ).build()*/
    //single { StatisticDataBase() }
