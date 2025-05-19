package com.example.countrieswalmart.domain.repository

import com.example.countrieswalmart.domain.data.Country

interface ICountryRepository {
    suspend fun getCountriesInfo():List<Country>
}