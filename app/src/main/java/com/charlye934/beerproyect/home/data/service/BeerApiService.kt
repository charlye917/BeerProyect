package com.charlye934.beerproyect.home.data.service

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.utils.BaseServiceBuilder
import com.charlye934.beerproyect.utils.Resources
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BeerApiService {
    @GET("v2/beers")
    suspend fun getListBeer(@Query("page") page:Int = 10): Response<List<Beer>>

    class Builder{
        fun build(): BeerApiService{
            return BaseServiceBuilder.getRetrofitInstance()
                .create(BeerApiService::class.java)
        }
    }
}