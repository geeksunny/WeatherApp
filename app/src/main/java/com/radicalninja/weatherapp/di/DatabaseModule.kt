package com.radicalninja.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.radicalninja.weatherapp.data.db.CitiesDao
import com.radicalninja.weatherapp.data.db.CitiesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): CitiesDatabase {
        return Room.databaseBuilder(application, CitiesDatabase::class.java, "cities")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCitiesDao(database: CitiesDatabase): CitiesDao {
        return database.citiesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCitiesDao(get()) }

}

