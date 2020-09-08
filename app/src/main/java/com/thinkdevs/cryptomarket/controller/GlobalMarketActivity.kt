package com.thinkdevs.cryptomarket.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.ADMOB_UNIT
import com.thinkdevs.cryptomarket.model.GlobalMarket
import com.thinkdevs.cryptomarket.model.Helper
import com.thinkdevs.cryptomarket.utils.CryptCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_global_market.*
import kotlinx.android.synthetic.main.toolbar.view.*

class GlobalMarketActivity : AppCompatActivity() {
	var composite: CompositeDisposable? = null
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_global_market)
		composite = CompositeDisposable()
		
		toolbar.coinImage.visibility = View.GONE
		toolbar.nav_share.visibility = View.GONE
		
		toolbar.nav_nack.setOnClickListener {
			onBackPressed()
		}
		
		globalMarket()
		
		adsmobs()
		
	}
	
	private fun globalMarket() {
		contrainview_.visibility = View.VISIBLE
		val apiService = Helper.mainUrl()
		composite!!.add(apiService.getglobal()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{ result ->
							getResult(result)
						},
						{ error ->
							contrainview_.visibility= View.VISIBLE
							no_internet.text = getString(R.string.no_connection)
							progressBar.visibility = View.GONE
							linearLayout2.visibility =View.GONE
							log("Errror ${error.message}")
						}
				))
		
	}
	
	private fun getResult(result: GlobalMarket) {
		contrainview_.visibility = View.GONE
		
//		totalMarket.text = String.format("%.3fBillion", result.total_market_cap_usd.toLong()/1000000000)
		totalMarket.text = " ${withSuffix(result.total_market_cap_usd.toLong())} Billion"
		total_24h.text = " ${withSuffix(result.total_24h_volume_usd.toLong())} Billion"
		bitcoinPerc.text = " ${result.bitcoin_percentage_of_market_cap} % "
		activeCurrency.text = result.active_currencies.toString()
		active_asset.text = result.active_assets.toString()
		active_market.text = result.active_markets.toString()
		last_update.text = result.last_updated.toString()
		
		val testing:Long = result.total_market_cap_usd.toLong()
		
	}
	
	private fun log(msg: String) {
		Log.e(javaClass.simpleName, msg)
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
		
		
	}
	
	fun withSuffix(count: Long): String {
		if (count < 1000) return "" + count
		val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
		return String.format("%.1f %c",
				count / Math.pow(1000.0, exp.toDouble()),
				"HM-TPE"[exp - 1])
	}
	
	private fun adsmobs() {
		if (CryptCurrency.isNetworkAvailable(this)) {
				CryptCurrency.logAmplitudeEvent("ADS_HOME")
			val isConnected = true
			if (isConnected) {
				val adView = AdView(this)
				adView.adSize = AdSize.BANNER
				adView.adUnitId = ADMOB_UNIT
				linearLayout4.addView(adView)
				val adResquest = AdRequest.Builder().build()
				global_ads.loadAd(adResquest)
			} else {
				println("IS NOT CONNECTED")
				
				linearLayout4.visibility = View.GONE
			}
		} else {
			linearLayout4.visibility = View.GONE
		}
		
		
	}
	
	
}

