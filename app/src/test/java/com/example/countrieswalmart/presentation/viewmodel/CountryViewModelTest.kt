package com.example.countrieswalmart.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countrieswalmart.domain.data.Country
import com.example.countrieswalmart.domain.usecase.CountryUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountryViewModel
    private val fetchCountriesUseCase: CountryUseCase = mockk()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCountries should emit Success state when countries are fetched successfully`() = runTest {

        val mockCountryList = listOf(Country( "Algiers",
            "DZ", countryName = "Algeria","AF"))
        coEvery { fetchCountriesUseCase() } returns mockCountryList

        viewModel = CountryViewModel(fetchCountriesUseCase, StandardTestDispatcher(testScheduler))

        advanceUntilIdle()
        val uiState = viewModel.uiState.take(1).toList()
        assertEquals(ApiState.Success(mockCountryList),
            uiState.first())
    }

    @Test
    fun `loadCountries should emit Error state when country list is empty`() = runTest {

        coEvery { fetchCountriesUseCase() } returns emptyList()

        viewModel = CountryViewModel(fetchCountriesUseCase, StandardTestDispatcher(testScheduler))
        advanceUntilIdle()
        val uiState = viewModel.uiState.take(1).toList()
        assertEquals(ApiState.Error("Empty Response from API"), uiState.first())
    }



    @Test
    fun `loadCountries should emit Error state when an exception is thrown`() = runTest {
        //Given
        val exception = RuntimeException("Network error")
        coEvery { fetchCountriesUseCase() } throws exception

        //When
        viewModel = CountryViewModel(fetchCountriesUseCase, StandardTestDispatcher(testScheduler))

        //Then
        advanceUntilIdle()
        val uiState = viewModel.uiState.take(1).toList()
        assertEquals(ApiState.Error("Error is : $exception"), uiState.first())
    }
}