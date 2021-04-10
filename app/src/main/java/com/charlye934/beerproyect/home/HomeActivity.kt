package com.charlye934.beerproyect.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.charlye934.beerproyect.R
import com.charlye934.beerproyect.databinding.ActivityHomeBinding
import com.charlye934.beerproyect.home.presentation.fragment.BeerInfoFragment
import com.charlye934.beerproyect.home.presentation.viewmodel.BeerViewModel
import com.charlye934.beerproyect.home.presentation.viewmodel.BeerViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModelFactory: BeerViewModelFactory
    lateinit var viewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            viewModelFactory = BeerViewModelFactory(application)
            viewModel = ViewModelProvider(this, viewModelFactory).get(BeerViewModel::class.java)
            changeFramgnet(BeerInfoFragment.newInstance(), BeerInfoFragment.TAG)
        }
    }

    private fun changeFramgnet(fragment: Fragment, tag:String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameHome, fragment)
            .addToBackStack(tag)
            .commit()
    }
}