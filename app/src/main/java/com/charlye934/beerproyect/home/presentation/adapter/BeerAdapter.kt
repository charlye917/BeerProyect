package com.charlye934.beerproyect.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.beerproyect.R
import com.charlye934.beerproyect.databinding.ItemBeerBinding
import com.charlye934.beerproyect.home.data.model.Beer
import com.charlye934.beerproyect.home.presentation.listener.BeerListener
import kotlinx.android.synthetic.main.item_beer.view.*

class BeerAdapter(
    private val beerList: ArrayList<Beer>,
    private val beerListener: BeerListener
    ) : RecyclerView.Adapter<BeerAdapter.NewsViewHolder>() {

    fun updateData(newBeerList:List<Beer>){
        beerList.clear()
        beerList.addAll(newBeerList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val beer = beerList[position]
        holder.bind(beer)
    }

    override fun getItemCount(): Int = beerList.size

    inner class NewsViewHolder(private val binding: ItemBeerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer){
            binding.tvNameBeer.text = beer.name
            binding.imgBeer.let {
                Glide.with(it)
                    .load(beer.imageUrl)
                    .error(R.drawable.ic_beer)
                    .into(binding.imgBeer)
            }
            binding.cardViewBeer.setOnClickListener {
                beerListener.saveBeer(beer)
                beerListener.goToBeerDetail()
            }
        }

    }
}