package com.example.countrieswalmart.domain.usecase

import com.example.countrieswalmart.data.repository.CountryRepository
import com.example.countrieswalmart.domain.data.Country
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.invoke


/**
 * Unit tests for the CountryUseCase class.
 * Instance of the use case being tested.
 * Mocked repository that will be used to provide fake data to the use case.
 */
class CountryUseCaseTest {


    private lateinit var useCase: CountryUseCase
    private var repository: CountryRepository = mockk(relaxed = true)

    /**
     * Sets up the test environment before each test.
     * Initializes the [CountryUseCase] with a mocked repository.
     */
    @Before
    fun setUp() {
        useCase = CountryUseCase(repository)
    }

    /**
     * Test case to verify that the use case returns a non-empty list of countries
     * when the repository returns a list of countries.
     * Assert: Check that the use case returns a non-empty list and matches the expected result.
     * Arrange: Define the expected result.
     * Act: Mock the repository to return the expected result.
     */
    @Test
    fun `Given Country API returns country list then useCase returns list`() = runTest {

        val expectedResult = listOf(Country("Washington DC", "US", "United States of America", "NA"))
        coEvery { repository.getCountriesInfo() } returns expectedResult
        assertTrue(useCase.invoke().isNotEmpty())
        val actualResult = useCase.invoke()
        assertEquals(actualResult, expectedResult)
    }

    /**
     * Test case to verify that the use case returns an empty list of countries
     * when the repository returns an empty list.
     * Act: Mock the repository to return an empty list.
     * Assert: Check that the use case returns an empty list.
     */
    @Test
    fun `Given Country API returns country empty list then useCase returns empty list`() = runTest {

        coEvery { repository.getCountriesInfo() } returns listOf()
        assertTrue(useCase.invoke().isEmpty())
    }
}