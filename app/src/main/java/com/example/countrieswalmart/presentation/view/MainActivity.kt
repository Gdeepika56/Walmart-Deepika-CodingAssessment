package com.example.countrieswalmart.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrieswalmart.data.remote.CountriesApiClient
import com.example.countrieswalmart.data.remote.CountriesApiService
import com.example.countrieswalmart.data.repository.CountryRepository
import com.example.countrieswalmart.databinding.ActivityMainBinding
import com.example.countrieswalmart.domain.usecase.CountryUseCase
import com.example.countrieswalmart.presentation.viewmodel.ApiState
import com.example.countrieswalmart.presentation.viewmodel.CountryViewModel
import com.example.countrieswalmart.presentation.viewmodel.CountryViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    /**
     * Binding object for the main UI components.
     * Adapter for managing the list of countries in the RecyclerView.
     * ViewModel instance for managing the country data and UI state.
     */

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var countryAdapter: CountriesAdapter
    private val viewModel: CountryViewModel by viewModels {
        // Initializes the necessary components for the ViewModel.
        val apiService = CountriesApiClient.retrofit.create(CountriesApiService::class.java)
        val countryRepository = CountryRepository(apiService)
        val countryUseCase = CountryUseCase(countryRepository)
        CountryViewModelFactory(countryUseCase)
    }

    /**
     * Sets up the RecyclerView for displaying country data.
     * Observes and displays the data from the ViewModel.
     * Triggers the loading of country data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        setUpRecyclerView()
        displayData()
        fetchCountriesData()

    }

    /**
     * Triggers the ViewModel to load the list of countries.
     */
    private fun fetchCountriesData() {
        viewModel.loadCountries()
    }

    /**
     * Observes the UI state from the ViewModel and updates the UI accordingly.
     */
    private fun displayData() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when(uiState) {
                    is ApiState.LoadingState -> {
                        // Show a progress bar while data is loading.
                        bindingMain.progressBarState.visibility = View.VISIBLE
                    }

                    is ApiState.Success -> {
                        // Hide the progress bar and display the list of countries.
                        bindingMain.progressBarState.visibility = View.GONE
                        countryAdapter.refreshCountries(uiState.countryList)
                    }

                    is ApiState.Error -> {
                        // Hide the progress bar and show an error message.
                        bindingMain.progressBarState.visibility = View.GONE
                        Toast.makeText(this@MainActivity, uiState.errorData, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    /**
     * Sets up the RecyclerView with a linear layout and initializes the adapter.
     */
    private fun setUpRecyclerView() {
        bindingMain.recyclerViewCountries.layoutManager = LinearLayoutManager(this)
        countryAdapter = CountriesAdapter(emptyList())
        bindingMain.recyclerViewCountries.adapter = countryAdapter
    }
}