package com.radicalninja.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.radicalninja.weatherapp.viewmodel.CitiesViewModel
import com.radicalninja.weatherapp.R
import com.radicalninja.weatherapp.data.adapter.CitiesAdapter
import com.radicalninja.weatherapp.databinding.FragmentCitiesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CitiesFragment : Fragment(), AdapterView.OnItemClickListener {

    private val citiesViewModel by viewModel<CitiesViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var viewDataBinding: FragmentCitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_cities, container, false)
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
        viewDataBinding.viewModel = citiesViewModel

//        citiesViewModel.showLoading.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                TODO("Not yet implemented")
//                // toggle loading indicator
//            }
//        })

        citiesViewModel.getAllCities()
        citiesViewModel.citiesList.observe(viewLifecycleOwner, {
            Log.d("Cities.Count", it.size.toString())
            if (it.isNotEmpty()) {
                citiesAdapter.setCities(it)
                citiesViewModel.getAllWeatherConditions()
                citiesViewModel.weatherPair.observe(viewLifecycleOwner, {
                    Log.d("Cities.Weather", "Weather updated.")
                    citiesAdapter.notifyDataSetChanged()
                })
            }
        })
    }

    private fun setView() {
        citiesAdapter = CitiesAdapter(context)
        viewDataBinding.rvCities.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewDataBinding.rvCities.adapter = citiesAdapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
        // todo Launch new screen for city 5-day forecast
//        (activity as MainActivity).replaceFragment(WeatherForecastFragment.newInstance(...), R.id.fragment_container, "city_forecast")
    }
}