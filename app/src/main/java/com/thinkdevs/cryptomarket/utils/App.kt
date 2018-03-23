package com.thinkdevs.cryptomarket.utils

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import com.amplitude.api.Amplitude
import com.google.android.gms.ads.MobileAds
import com.splunk.mint.Mint
import com.thinkdevs.cryptomarket.BuildConfig
import com.thinkdevs.cryptomarket.constant.ADMOB_ID_BANNER
import com.thinkdevs.cryptomarket.constant.ADMOB_ID_BANNER_TEST
import com.thinkdevs.cryptomarket.constant.AMPLITUTDE_KEY

/**
 * Created by kevinjanvier on 22/03/2018.
 */
class App :Application() {
	companion object {
		init {
			AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
		}
	}
	
	
	override fun onCreate() {
		super.onCreate()
		
		if(BuildConfig.DEBUG){
			log("==============DEBUG MODE================")
			//Production
			MobileAds.initialize(this, ADMOB_ID_BANNER_TEST)
		
		}else{
			//test
			trackbugs()
			log("==============PRODUCTION MODE=============")
			MobileAds.initialize(this, ADMOB_ID_BANNER)
			Amplitude.getInstance().initialize(this, AMPLITUTDE_KEY).enableForegroundTracking(this)
			
			
			
			
		}
	}
	
	private fun trackbugs() {
		Mint.initAndStartSession(this@App, "8073f5b3")
		
	}
	
	
	private fun log(msg: String) {
		Log.e(this.javaClass.simpleName, msg)
	}
}