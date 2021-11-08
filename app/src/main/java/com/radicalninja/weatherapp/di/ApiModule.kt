package com.radicalninja.weatherapp.di

import com.radicalninja.weatherapp.data.api.CitiesApi
import com.radicalninja.weatherapp.data.api.WeatherApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCitiesApi(retrofit: Retrofit): CitiesApi {
        return retrofit.create(CitiesApi::class.java)
    }

    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    single { provideCitiesApi(get(named("citiesRetrofit"))) }
    single { provideWeatherApi(get(named("weatherRetrofit"))) }

}
