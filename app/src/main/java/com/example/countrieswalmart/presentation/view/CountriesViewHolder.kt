package com.example.countrieswalmart.presentation.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieswalmart.databinding.ViewHolderCountriesBinding
import com.example.countrieswalmart.domain.data.Country

/**
 * ViewHolder class for displaying a single country item in a RecyclerView.
 */
class CountriesItemViewHolder(private val binding: ViewHolderCountriesBinding) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Binds the country data to the UI components in the country item layout.
     * Sets the text for the country name and region.
     * Sets the text for the capital city.
     * Sets the text for the country code
     */
    @SuppressLint("SetTextI18n")
    fun bindDataWithUi(country: Country) {
        with(binding) {
            countryNamePlusRegion.text = "${country.countryName}, ${country.locatedRegion}"
            capitalCity.text = country.capitalCity
            countryCode.text = country.countryCode
        }
    }
}