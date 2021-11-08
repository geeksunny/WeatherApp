package com.radicalninja.weatherapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeatherCondition(
        @Query("appid") apikey: String,
        @Query("q") query: String,
        @Query("units") units: String
    ): Response<WeatherConditionApiResponse>

}