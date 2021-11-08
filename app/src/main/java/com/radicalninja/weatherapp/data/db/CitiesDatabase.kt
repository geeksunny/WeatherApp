package com.radicalninja.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.data.model.WeatherConditionsData

@Database(
    entities = [CitiesData::class, WeatherConditionsData::class],
    version = 1, exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class CitiesDatabase : RoomDatabase() {
    abstract val citiesDao: CitiesDao
}