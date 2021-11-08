package com.radicalninja.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.data.model.WeatherConditionsData

@Dao
interface CitiesDao {

    @Query("SELECT * FROM Cities")
    fun getAll(): List<CitiesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(cities: List<CitiesData>)

    @Query("SELECT * FROM Conditions WHERE cityId = :cityId")
    fun getForecastForCity(cityId: Int): WeatherConditionsData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeCondition(condition: WeatherConditionsData)

}