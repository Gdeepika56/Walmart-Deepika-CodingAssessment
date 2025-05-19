package com.example.countrieswalmart.data.mapper

import com.example.countrieswalmart.data.entities.CountriesEntity
import com.example.countrieswalmart.domain.data.Country

/**
 * Mapper object that converts CountriesEntity objects to Country objects.
 */
object CountryMapper : Mapper<CountriesEntity, Country> {

    /**
     * Converts a CountriesEntity object to a Country object.
     */
    override fun mapToDomain(type: CountriesEntity): Country {
        return Country(
            countryName = type.name,
            capitalCity = type.capital,
            countryCode = type.code,
            locatedRegion = type.region
        )
    }

    /**
     * Converts a list of CountriesEntity objects to a list of Country objects.
     */
    fun mapToDomainList(countryEntities: List<CountriesEntity>) = countryEntities.map { item -> mapToDomain(item) }
}
