package com.charlye934.beerproyect.home.domain

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.utils.Resources

interface BeerInteractor {
    suspend fun getBeerInfo(page:Int): Resources<List<Beer>>
}