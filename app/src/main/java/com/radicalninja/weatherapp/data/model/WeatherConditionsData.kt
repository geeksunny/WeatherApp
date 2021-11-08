package com.radicalninja.weatherapp.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Conditions", indices = [Index(value = ["id", "cityId"])])
data class WeatherConditionsData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val cityId: Int,
    val temperatureCurrent: Float,
    val temperatureHigh: Float,
    val temperatureLow: Float,
    val description: String,
    val icon: String,
    val updateTime: Date
)