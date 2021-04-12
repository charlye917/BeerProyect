package com.charlye934.beerproyect.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private val BASE_URL = "https://api.punkapi.com/"

    fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}