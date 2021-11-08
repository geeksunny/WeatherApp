package com.radicalninja.weatherapp.data.api

import com.radicalninja.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CitiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Parse-Application-Id", BuildConfig.CITIES_APP_ID)
            .addHeader("X-Parse-Master-Key", BuildConfig.CITIES_API_KEY)
            .build()
        return chain.proceed(request)
    }
}