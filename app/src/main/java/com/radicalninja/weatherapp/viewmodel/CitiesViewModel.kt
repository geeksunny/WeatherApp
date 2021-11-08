package com.radicalninja.weatherapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radicalninja.weatherapp.data.api.Resource
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.data.model.WeatherConditionsData
import com.radicalninja.weatherapp.data.repository.CitiesRepository
import com.radicalninja.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class CitiesViewModel(private val citiesRepository: CitiesRepository, private val weatherRepository: WeatherRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val citiesList = MutableLiveData<List<CitiesData>>()
    val weatherPair = MutableLiveData<Pair<CitiesData, WeatherConditionsData>>()
    val showError = ObservableBoolean()

    fun getAllCities() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = citiesRepository.getAllCities()
            showLoading.set(false)
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("CitiesViewModel", "Success")
                    citiesList.value = result.data!!
                }
                Resource.Status.ERROR -> {
                    Log.d("CitiesViewModel", result.message?:"Unknown error")
                    showError.set(true)
                }
            }
        }
    }

    fun getAllWeatherConditions() {
        //showLoading.set(true)
        viewModelScope.launch {
            for (city in citiesList.value!!) {
                val condition = weatherRepository.getCurrentWeatherCondition(city)
                when (condition.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d("Weather", "Weather success")
                        citiesList.value?.find { it.id == city.id }?.weatherConditionsData =
                            condition.data!!
                        weatherPair.value = Pair(city, condition.data)
                    }
                    Resource.Status.ERROR -> {
                        // todo
                        Log.d("Weather.error", condition.message ?: "Unknown error")
                    }
                }
            }
        }
    }

}