package com.example.countrieswalmart.data.repository

import com.example.countrieswalmart.data.mapper.CountryMapper
import com.example.countrieswalmart.data.remote.CountriesApiService
import com.example.countrieswalmart.domain.data.Country
import com.example.countrieswalmart.domain.repository.ICountryRepository

class CountryRepository(private val apiService: CountriesApiService) : ICountryRepository {

    /**
     *  // Fetches country entities from the API and maps them to domain models using the CountryMapper.
     */
    override suspend fun getCountriesInfo(): List<Country> {
        return CountryMapper.mapToDomainList(apiService.getCountriesInfo())
    }
}