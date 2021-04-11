package com.charlye934.beerproyect.home.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.charlye934.beerproyect.databinding.FragmentBeerInfoBinding
import com.charlye934.beerproyect.home.HomeActivity
import com.charlye934.beerproyect.home.presentation.adapter.BeerAdapter
import com.charlye934.beerproyect.home.presentation.viewmodel.BeerViewModel
import com.charlye934.beerproyect.utils.Resources

class BeerInfoFragment : Fragment() {

    private lateinit var binding: FragmentBeerInfoBinding
    private lateinit var viewModel: BeerViewModel
    private lateinit var listener: HomeActivity
    private lateinit var adapterBeer: BeerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBeerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            viewModel = listener.viewModel
            adapterBeer = BeerAdapter(
                arrayListOf(),
                listener
            )

            getDataBeer()
            initRecyclerView()
            setOnClickAction()
        }
    }

    private fun initRecyclerView(){
        binding.recyclerBeer.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = adapterBeer
        }
    }

    private fun setOnClickAction(){
        binding.refresh.setOnRefreshListener { getDataBeer() }
    }

    private fun getDataBeer(){
        showProgresBar()
        binding.refresh.isRefreshing = false
        viewModel.getBeer()
        viewModel.beerLiveData.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resources.Success ->{
                    hideProgresBar()
                    adapterBeer.updateData(response.data!!)
                }
                is Resources.Error -> {
                    hideProgresBar()
                    response.message?.let {
                        Toast.makeText(context, "An error ocurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resources.Loading ->{
                    showProgresBar()
                }
            }
        }
    }

    private fun showProgresBar(){
        binding.progresBar.visibility = View.VISIBLE
    }

    private fun hideProgresBar(){
        binding.progresBar.visibility = View.INVISIBLE
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