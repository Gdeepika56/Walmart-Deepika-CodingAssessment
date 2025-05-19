package com.example.countrieswalmart.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieswalmart.databinding.ViewHolderCountriesBinding
import com.example.countrieswalmart.domain.data.Country

class CountriesAdapter (private var countries: List<Country>) : RecyclerView.Adapter<CountriesItemViewHolder>() {

    /**
     * Binding object for the country item layout.
     */
    private lateinit var binding: ViewHolderCountriesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ViewHolderCountriesBinding.inflate(inflater, parent, false)
        return CountriesItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesItemViewHolder, position: Int) {
        holder.bindDataWithUi(countries[position])
    }

    override fun getItemCount() = countries.size

    /**
     * Updates the list of countries and notifies the RecyclerView that the data set has changed.
     * Notify the adapter that the data set has changed.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun refreshCountries(newData: List<Country>) {
        countries = newData
        notifyDataSetChanged()
    }
}