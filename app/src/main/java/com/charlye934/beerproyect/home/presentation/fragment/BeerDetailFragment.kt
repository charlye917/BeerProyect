package com.charlye934.beerproyect.home.presentation.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.charlye934.beerproyect.R
import com.charlye934.beerproyect.databinding.FragmentDetailBinding
import com.charlye934.beerproyect.home.HomeActivity
import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.data.model.BeerPalet
import com.charlye934.beerproyect.home.presentation.viewmodel.BeerViewModel

class BeerDetailFragment : Fragment() {

    private lateinit var listener: HomeActivity
    private lateinit var viewModel: BeerViewModel
    private lateinit var binding: FragmentDetailBinding
    private lateinit var beer:Beer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            viewModel = listener.viewModel
            beer = viewModel.getData()
            setData()
        }
    }

    private fun setData(){
        setupBackgroundColor(beer.imageUrl)

        binding.imgBeer.let {
            Glide.with(it)
                .load(beer.imageUrl)
                .error(R.drawable.ic_beer)
                .into(binding.imgBeer)
        }

        binding.tvNameBeer.text = beer.name
        binding.tvDescriptionBeer.text = beer.description
        binding.tvTaglinenBeer.text = beer.tagline
        binding.tvFirstBrewed.text = beer.firstBrewed
        binding.tvFoodPairing.text = getFoodPairing()
        binding.tvBrewersTips.text = beer.brewers_tips
    }

    private fun getFoodPairing():String{
        var result = ""
        for(i in 0 until beer.foodPairing.size){
            result = "- " + beer.foodPairing[i] + "\n" + result
        }
        return result
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            val myPalette = BeerPalet(intColor)
                            binding.palette = myPalette
                            Log.d("__tag", binding.palette.toString())
                        }
                }

            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as HomeActivity
    }

    companion object {
        val TAG = BeerDetailFragment::class.java.simpleName
        fun newInstace() = BeerDetailFragment()
    }
}