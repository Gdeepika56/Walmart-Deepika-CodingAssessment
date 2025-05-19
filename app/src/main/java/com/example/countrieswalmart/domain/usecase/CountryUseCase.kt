package com.example.countrieswalmart.domain.usecase

import com.example.countrieswalmart.domain.data.Country
import com.example.countrieswalmart.domain.repository.ICountryRepository

/**
 * Use case class that provides a list of countries by interacting with the ICountryRepository.
 */
class CountryUseCase(private val repository: ICountryRepository) {

    /**
     * Invokes the use case to fetch a list of countries from the repository.
     */
    suspend operator fun invoke(): List<Country> = repository.getCountriesInfo()
}