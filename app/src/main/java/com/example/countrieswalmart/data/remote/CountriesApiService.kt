package com.example.countrieswalmart.data.remote

import com.example.countrieswalmart.data.commons.Constants.END_POINT
import com.example.countrieswalmart.data.entities.CountriesEntity
import retrofit2.http.GET

interface CountriesApiService {
    @GET(END_POINT)
    suspend fun getCountriesInfo():List<CountriesEntity>
}