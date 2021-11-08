package com.radicalninja.weatherapp.view

import com.radicalninja.weatherapp.data.model.CitiesData

interface OnCityClickListener {

    fun onClick(city: CitiesData)

}