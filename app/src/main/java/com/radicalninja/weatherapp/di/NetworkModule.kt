package com.radicalninja.weatherapp.di

import com.radicalninja.weatherapp.BuildConfig
import com.radicalninja.weatherapp.data.api.CitiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig.DEBUG
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout = 40L
    val readTimeout = 40L

    fun initHttpClient(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder
    }

    fun provideCitiesRetrofit(): Retrofit {
        val client = initHttpClient()
            .addInterceptor(CitiesInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.CITIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun provideWeatherRetrofit(): Retrofit {
        val client = initHttpClient().build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single(named("citiesRetrofit")) { provideCitiesRetrofit() }
    single(named("weatherRetrofit")) { provideWeatherRetrofit() }
}