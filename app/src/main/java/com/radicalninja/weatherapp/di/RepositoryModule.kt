package com.radicalninja.weatherapp.di

import android.content.Context
import com.radicalninja.weatherapp.data.api.CitiesApi
import com.radicalninja.weatherapp.data.api.WeatherApi
import com.radicalninja.weatherapp.data.db.CitiesDao
import com.radicalninja.weatherapp.data.repository.CitiesRepository
import com.radicalninja.weatherapp.data.repository.CitiesRepositoryImpl
import com.radicalninja.weatherapp.data.repository.WeatherRepository
import com.radicalninja.weatherapp.data.repository.WeatherRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCityRepository(api: CitiesApi, context: Context, dao: CitiesDao): CitiesRepository {
        return CitiesRepositoryImpl(api, context, dao)
    }

    fun provideWeatherRepository(api: WeatherApi, context: Context, dao: CitiesDao): WeatherRepository {
        return WeatherRepositoryImpl(api, context, dao)
    }

    single { provideCityRepository(get(), androidContext(), get()) }
    single(named("weatherRepository")) { provideWeatherRepository(get(), androidContext(), get()) }

}