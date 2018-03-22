package com.thinkdevs.cryptomarket.utils

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder

/**
 * Created by kevinjanvier on 22/03/2018.
 */
object CryptCurrency {
	fun toStringGson() = GsonBuilder().setPrettyPrinting().create()
	
	
	fun isNetworkAvailable(context: Context): Boolean {
		val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetworkInfo = connectivityManager.activeNetworkInfo
		return activeNetworkInfo != null && activeNetworkInfo.isConnected
	}
	
	
}