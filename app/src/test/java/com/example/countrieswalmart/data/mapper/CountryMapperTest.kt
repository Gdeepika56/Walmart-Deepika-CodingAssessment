package com.example.countrieswalmart.data.mapper

import com.example.countrieswalmart.data.entities.CountriesEntity
import com.example.countrieswalmart.domain.data.Country
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CountryMapperTest {

    /**
     * Test to verify that [CountryMapper.mapToDomain] returns a [Country] object
     * when invoked with a [CountriesEntity] object.
     */
    @Test
    fun `mapToDomain is invoked with CountriesEntity then Country is returned`() {
        val res = CountryMapper.mapToDomain(CountriesEntity("Washington DC", "US", mockk(), "", "", mockk(), "", ""))
        assertEquals("US", res.countryCode)
    }

    /**
     * Test to verify that [CountryMapper.mapToDomainList] returns a list of [Country] objects
     * when invoked with a list of [CountriesEntity] objects.
     */
    @Test
    fun `mapToDomainList is invoked with list of CountriesEntity then list of Country is returned`() {
        val res = CountryMapper.mapToDomainList(
            listOf(CountriesEntity("Washington DC", "US", mockk(), "", "", mockk(), "", ""))
        )
        assertTrue(res.isNotEmpty())
        assertTrue(res.all { it is Country })
    }
}