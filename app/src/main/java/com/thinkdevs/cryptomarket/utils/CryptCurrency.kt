package com.thinkdevs.cryptomarket.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.amplitude.api.Amplitude
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject

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
	
	
	
	/**
	 * Log amplitude Event
	 *
	 * @param eventName
	 */
	fun logAmplitudeEvent(eventName: String) {
		log("======trackEvent")
		log("My Event Name:$eventName")
		
		val deviceId = Amplitude.getInstance().deviceId
		Amplitude.getInstance("Livescore").deviceId = deviceId
		Amplitude.getInstance().trackSessionEvents(true)
		
		Amplitude.getInstance().logEvent(eventName)
		
	}
	
	
	fun logAmplitudeEvent(eventName: String, propertyName: String, property: String) {
		log(":::::::::: logAmplitudeEvent")
		log("===== eventName:$eventName")
		log("===== propertyName:$propertyName")
		log("===== property:$property")
		
		val eventProperties = JSONObject()
		try {
			eventProperties.put(propertyName, property)
		} catch (exception: JSONException) {
		}
		
		Amplitude.getInstance().logEvent(eventName, eventProperties)
	}
	
	fun log(msg:String){
		Log.e(javaClass.simpleName, msg)
	}
	
}