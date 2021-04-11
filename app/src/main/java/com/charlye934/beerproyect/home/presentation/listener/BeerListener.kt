package com.charlye934.beerproyect.home.presentation.listener

import com.charlye934.beerproyect.home.data.model.Beer

interface BeerListener {
    fun goToBeerDetail()
    fun saveBeer(beer:Beer)
    fun showNavigation()
    fun hideNavigation()
}