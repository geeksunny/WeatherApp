package com.radicalninja.weatherapp.data.repository

import android.content.Context
import com.radicalninja.weatherapp.BuildConfig
import com.radicalninja.weatherapp.R
import com.radicalninja.weatherapp.data.api.Resource
import com.radicalninja.weatherapp.data.api.WeatherApi
import com.radicalninja.weatherapp.data.db.CitiesDao
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.data.model.WeatherConditionsData
import com.radicalninja.weatherapp.util.NetworkUtil.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

interface WeatherRepository {
    suspend fun getCurrentWeatherCondition(city: CitiesData): Resource<WeatherConditionsData>
}

class WeatherRepositoryImpl(private val api: WeatherApi, private val context: Context, private val dao: CitiesDao): WeatherRepository {

    override suspend fun getCurrentWeatherCondition(city: CitiesData): Resource<WeatherConditionsData> {
        if (isOnline(context)) {
            // pull from api
            return try {
                val query = String.format(context.getString(R.string.weather_query_template, city.name, city.state))
                val response = api.getCurrentWeatherCondition(BuildConfig.WEATHER_API_KEY, query, "imperial")
                if (response.isSuccessful) {
                    val value = WeatherConditionsData(
                        0,
                        city.id,
                        response.body()!!.temperature.actual,
                        response.body()!!.temperature.temp_max,
                        response.body()!!.temperature.temp_min,
                        response.body()!!.conditions[0].description,
                        response.body()!!.conditions[0].icon,
                        Date()
                    )
                    // save data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.storeCondition(value) }
                    }
                    Resource.success(value)
                } else {
                    Resource.error("API Error")
                }
            } catch (e: Exception) {
                Resource.error(e.message ?: "invalid error")
            }
        } else {
            // pull from cache
            val data = getWeatherConditionFromCache(city)
            return if (data != null) {
                Resource.success(data)
            } else {
                Resource.error("No network or cached data.")
            }
        }
    }

    private suspend fun getWeatherConditionFromCache(city: CitiesData): WeatherConditionsData? {
        return withContext(Dispatchers.IO) {
            dao.getForecastForCity(city.id)
        }
    }

}