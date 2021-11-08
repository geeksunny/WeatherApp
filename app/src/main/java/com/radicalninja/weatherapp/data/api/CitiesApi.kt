package com.radicalninja.weatherapp.data.api

import retrofit2.Response
import retrofit2.http.GET

interface CitiesApi {

    @GET("States?keys=capital,postalAbreviation")
    suspend fun getAllCities(): Response<CitiesApiResponse>

}