package com.charlye934.beerproyect.home.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.charlye934.beerproyect.R
import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.data.service.BeerApiService
import com.charlye934.beerproyect.home.domain.BeerInteractor
import com.charlye934.beerproyect.home.domain.BeerInteractorImp
import com.charlye934.beerproyect.utils.CheckConnection
import com.charlye934.beerproyect.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class BeerViewModel(private val app:Application) : ViewModel() {
    private val beerInteractor: BeerInteractor = BeerInteractorImp()
    val beerLiveData: MutableLiveData<Resources<List<Beer>>> = MutableLiveData()

    fun getBeer() = viewModelScope.launch(Dispatchers.IO) {
        beerLiveData.postValue(Resources.Loading())
        try {
            if(CheckConnection.isNetworkAvaible(app)){
                beerLiveData.postValue(beerInteractor.getBeerInfo())
            }else{
                beerLiveData.postValue(Resources.Error(app.getString(R.string.internet_not_avaible)))
            }
        }catch (e: Exception){
            beerLiveData.postValue(Resources.Error(e.message.toString()))
        }
    }
}