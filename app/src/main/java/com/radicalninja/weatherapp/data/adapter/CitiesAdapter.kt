package com.radicalninja.weatherapp.data.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.radicalninja.weatherapp.R
import com.radicalninja.weatherapp.data.model.CitiesData
import com.radicalninja.weatherapp.data.model.WeatherConditionsData
import com.radicalninja.weatherapp.databinding.ItemCityBinding

class CitiesAdapter(val context: Context?) : RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    private var citiesList: List<CitiesData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val viewBinding: ItemCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_city,
            parent,
            false)
        return CitiesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setCities(cities: List<CitiesData>) {
        this.citiesList = cities
        notifyDataSetChanged()
    }

    inner class CitiesViewHolder(private val viewBinding: ItemCityBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(position: Int) {
//            Log.d("Cities.bind", "position binding... " + position)
            val row = citiesList[position]
            viewBinding.city = row
            viewBinding.weatherCondition = row.weatherConditionsData
        }
    }

}