package com.charlye934.beerproyect.home.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("tagline")
    val tagline:String,
    @SerializedName("first_brewed")
    val firstBrewed:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("image_url")
    val imageUrl:String,
    @SerializedName("food_pairing")
    val foodPairing:List<String>,
    @SerializedName("brewers_tips")
    val brewers_tips:String
): Parcelable

data class BeerPalet(var color:Int)