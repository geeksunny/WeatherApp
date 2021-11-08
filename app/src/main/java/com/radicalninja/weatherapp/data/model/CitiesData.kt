package com.radicalninja.weatherapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Cities")
data class CitiesData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("capital") val name: String,
    @SerializedName("postalAbreviation") val state: String
) {
    @Ignore var weatherConditionsData: WeatherConditionsData? = null
}