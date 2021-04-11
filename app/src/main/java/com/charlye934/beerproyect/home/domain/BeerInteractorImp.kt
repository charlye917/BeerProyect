package com.charlye934.beerproyect.home.domain

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.data.repository.BeerRepository
import com.charlye934.beerproyect.home.data.repository.BeerRepositoryImp
import com.charlye934.beerproyect.utils.Resources

class BeerInteractorImp : BeerInteractor{

    private val beerRepository: BeerRepository = BeerRepositoryImp()

    override suspend fun getBeerInfo(page:Int): Resources<List<Beer>> {
        return beerRepository.getBeerInfo(page)
    }
}