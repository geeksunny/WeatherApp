package com.radicalninja.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.radicalninja.weatherapp.R
import com.radicalninja.weatherapp.util.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCitiesFragment()
    }

    fun addCitiesFragment() {
        replaceFragment(CitiesFragment(), R.id.fragment_container)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}