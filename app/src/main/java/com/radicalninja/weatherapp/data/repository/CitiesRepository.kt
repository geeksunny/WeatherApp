package com.radicalninja.weatherapp.data.repository

import android.content.Context
import com.radicalninja.weatherapp.data.api.CitiesApi
import com.radicalninja.weatherapp.data.api.Resource
import com.radicalninja.weatherapp.data.db.CitiesDao
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.util.NetworkUtil.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface CitiesRepository {
    suspend fun getAllCities(): Resource<List<CitiesData>>
}

class CitiesRepositoryImpl(private val api: CitiesApi, private val context: Context, private val dao: CitiesDao) : CitiesRepository {

    override suspend fun getAllCities(): Resource<List<CitiesData>> {
        // check cache
        val data = getCitiesDataFromCache()
        return when {
            data.isNotEmpty() -> {
                // return cached data if exists
                Resource.success(data)
            }
            isOnline(context) -> {
                // pull from API
                try {
                    val response = api.getAllCities()
                    if (response.isSuccessful) {
                        // save data
                        response.body()?.let {
                            withContext(Dispatchers.IO) { dao.add(it.results) }
                        }
                        Resource.success(response.body()!!.results)
                    } else {
                        // error
                        Resource.error("API Error")
                    }
                } catch (e: Exception) {
                    Resource.error(e.message ?: "invalid error")
                }
            }
            else -> {
                // Error state: no data to serve
                Resource.error("No network or cached data.")
            }
        }
    }

    private suspend fun getCitiesDataFromCache(): List<CitiesData> {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }

}