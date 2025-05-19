package com.example.countrieswalmart.data.repository

import com.example.countrieswalmart.data.entities.CountriesEntity
import com.example.countrieswalmart.data.remote.CountriesApiService
import com.example.countrieswalmart.domain.data.Country
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the CountryRepository class.
 * Instance of the repository being tested.
 * Mocked API service that provides fake data for the repository.
 */
class CountryRepositoryTest {


    private lateinit var repository: CountryRepository


    private val countriesApiService = mockk<CountriesApiService>(relaxed = true)

    /**
     * Sets up the test environment before each test.
     * Initializes the CountryRepository with a mocked API service.
     */
    @Before
    fun setUp() {
        repository = CountryRepository(countriesApiService)
    }

    /**
     * Test case to verify that the repository returns a list of countries
     * when the API service returns a list of country entities.
     * Arrange: Define the expected result.
     * Act: Mock the API service to return a list of country entities.
     * Assert: Check that the repository returns a list of countries that matches the expected result.
     */
    @Test
    fun `Given CountriesApiService returns list repository returns list`() = runTest {

        val actualResult = Country("Washington DC", "US", "USA", "NA")


        coEvery { countriesApiService.getCountriesInfo() } returns listOf(
            CountriesEntity("Washington DC", "US", mockk(), "", "", mockk(), "USA", "NA")
        )

        val result = repository.getCountriesInfo()
        assertEquals(result.first(), actualResult)
    }

    /**
     * Test case to verify that the repository returns an empty list
     * when the API service returns an empty list of country entities.
     * Act: Mock the API service to return an empty list.
     * Assert: Check that the repository returns an empty list.
     */
    @Test
    fun `Given CountriesApiService returns emptyList repository returns emptyList`() = runTest {

        coEvery { countriesApiService.getCountriesInfo() } returns listOf()

        val result = repository.getCountriesInfo()
        assertTrue(result.isEmpty())
    }
}