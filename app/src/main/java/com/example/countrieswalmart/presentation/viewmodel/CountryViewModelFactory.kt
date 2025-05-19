package com.example.countrieswalmart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrieswalmart.domain.usecase.CountryUseCase

class CountryViewModelFactory(private val countryUseCase: CountryUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(countryUseCase) as T
    }
}