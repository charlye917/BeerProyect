package com.charlye934.beerproyect.utils

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.data.service.BeerApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private val BASE_URL = "https://api.punkapi.com/"

    fun  getRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}