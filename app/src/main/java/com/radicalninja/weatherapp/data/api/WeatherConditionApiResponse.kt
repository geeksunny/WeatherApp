package com.radicalninja.weatherapp.data.api

import com.google.gson.annotations.SerializedName

data class WeatherConditionApiResponse(
    @SerializedName("weather") val conditions: List<Condition>,
    @SerializedName("main") val temperature: Temperature
)

data class Condition(
    val id: Int,
    @SerializedName("main") val name: String,
    val description: String,
    val icon: String
)

data class Temperature(
    @SerializedName("temp") val actual: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float
)