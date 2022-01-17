package com.zero.weatherapptest.network.retrofit

import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import com.zero.weatherapptest.data.constants.Constants
import com.zero.weatherapptest.network.interceptors.ParamsInterceptor
import com.zero.weatherapptest.network.interceptors.ResponseInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryImplement(private val paramsInterceptor: ParamsInterceptor,
                               private val responseInterceptor: ResponseInterceptor,
                               private val chuckInterceptor: ChuckInterceptor
) : RetrofitFactory {

    override fun createRetrofit(gson: Gson): Retrofit {
        val okHttpBuilder =
            OkHttpClient().newBuilder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.addInterceptor(chuckInterceptor)
        okHttpBuilder.addInterceptor(paramsInterceptor)
            .addInterceptor(responseInterceptor)
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        val builder = Retrofit.Builder()
            .baseUrl(Constants.CITY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())

        return builder.build()
    }
}