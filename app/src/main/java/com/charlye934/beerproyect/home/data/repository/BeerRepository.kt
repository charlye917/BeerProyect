package com.charlye934.beerproyect.home.data.repository

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.utils.Resources
import retrofit2.Response

interface BeerRepository {
    suspend fun getBeerInfo(page:Int): Resources<List<Beer>>
}