package com.radicalninja.weatherapp.di

import com.radicalninja.weatherapp.viewmodel.CitiesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CitiesViewModel(get(), get(named("weatherRepository")))
    }

}