package com.charlye934.beerproyect.home.data.repository

import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.data.service.BeerApiService
import com.charlye934.beerproyect.utils.Resources
import retrofit2.Response

class BeerRepositoryImp: BeerRepository {

    private val beerApiService = BeerApiService.Builder().build()

    override suspend fun getBeerInfo(): Resources<List<Beer>> {
        return responseToResources(beerApiService.getListBeer())
    }

    private fun responseToResources(response: Response<List<Beer>>): Resources<List<Beer>>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resources.Success(it)
            }
        }
        return Resources.Error(response.message())
    }
}