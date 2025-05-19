package com.example.countrieswalmart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieswalmart.domain.usecase.CountryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the UI state for the country list.
 * Backing property for UI state, starts with the Loading state.
 */
class CountryViewModel(private val fetchCountriesUseCase: CountryUseCase, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiState>(ApiState.LoadingState)

    /**
     * Publicly exposed immutable UI state flow.
     */
    val uiState: StateFlow<ApiState> = _uiState

    /**
     * Initializes the ViewModel by loading the countries.
     */
    init {
        loadCountries()
    }

    /**
     * Loads the list of countries using the provided use case and updates the UI state accordingly.
     * Handles exceptions by emitting an error state with the exception message.
     */
    fun loadCountries() {
        viewModelScope.launch(dispatcher) {
            try {
                _uiState.emit(ApiState.LoadingState)
                val countryList = fetchCountriesUseCase()
                if (countryList.isEmpty()) {
                    _uiState.emit(ApiState.Error("Empty Response from API"))
                } else {
                    _uiState.emit(ApiState.Success(countryList))
                }
            }
            catch(exception:Exception){
                _uiState.emit(ApiState.Error("Error is : $exception"))
            }
        }
    }
}