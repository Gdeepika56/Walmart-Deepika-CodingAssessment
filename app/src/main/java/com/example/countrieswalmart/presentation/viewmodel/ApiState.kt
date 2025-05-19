package com.example.countrieswalmart.presentation.viewmodel

import com.example.countrieswalmart.domain.data.Country


sealed class ApiState {
    data class Success(val countryList: List<Country>) : ApiState()
    data class Error(val errorData: String) : ApiState()
    data object LoadingState : ApiState()
}