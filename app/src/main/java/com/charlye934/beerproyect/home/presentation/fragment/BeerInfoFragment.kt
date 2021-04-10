package com.charlye934.beerproyect.home.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charlye934.beerproyect.R
import com.charlye934.beerproyect.databinding.FragmentBeerInfoBinding
import com.charlye934.beerproyect.home.HomeActivity
import com.charlye934.beerproyect.home.presentation.viewmodel.BeerViewModel
import com.charlye934.beerproyect.utils.Resources

class BeerInfoFragment : Fragment() {

    private lateinit var binding: FragmentBeerInfoBinding
    private lateinit var viewModel: BeerViewModel
    private lateinit var listener: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBeerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            viewModel = listener.viewModel
            getDataBeer()
        }
    }

    private fun getDataBeer(){
        viewModel.getBeer()
        viewModel.beerLiveData.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resources.Success ->{
                    Log.d("__TAG", response.data.toString())
                }
                is Resources.Error -> {

                }
                is Resources.Loading ->{

                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = activity as HomeActivity
    }

    companion object {
        val TAG = BeerInfoFragment::class.java.simpleName
        fun newInstance() = BeerInfoFragment()
    }
}