package com.charlye934.beerproyect.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object CheckConnection {
    fun isNetworkAvaible(context: Context): Boolean{
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
            val currentNetwork = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(currentNetwork != null){
                when{
                    currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->{
                        return true
                    }
                    currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                        return true
                    }
                    currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->{
                        return true
                    }
                }
            }else{
                val activityNetworkInfo = connectivityManager.activeNetworkInfo
                if(activityNetworkInfo != null && activityNetworkInfo.isConnected)
                    return true
            }
        }
        return false
    }
}