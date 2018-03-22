package com.thinkdevs.cryptomarket.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.GlobalMarket
import com.thinkdevs.cryptomarket.model.Helper
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
							log("Errror ${error.message}")
						}
				))
		
	}
	
	private fun getResult(result: GlobalMarket) {
		contrainview_.visibility = View.GONE
		
		log("result $result")
		log("Total====== ${result.bitcoin_percentage_of_market_cap}")
//		totalMarket.text = String.format("%.3fBillion", result.total_market_cap_usd.toLong()/1000000000)
		totalMarket.text = " ${withSuffix(result.total_market_cap_usd.toLong())} Billion"
		total_24h.text = " ${withSuffix(result.total_24h_volume_usd.toLong())} Billion"
		bitcoinPerc.text = " ${result.bitcoin_percentage_of_market_cap} % "
		activeCurrency.text = result.active_currencies.toString()
		active_asset.text = result.active_assets.toString()
		active_market.text = result.active_markets.toString()
		last_update.text = result.last_updated.toString()
		
		val testing:Long = result.total_market_cap_usd.toLong()
		
		log("Coin Base --- ${withSuffix(testing)}")
	}
	
	private fun log(msg: String) {
		Log.e(javaClass.simpleName, msg)
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
	}
	
	fun withSuffix(count: Long): String {
		if (count < 1000) return "" + count
		val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
		return String.format("%.1f %c",
				count / Math.pow(1000.0, exp.toDouble()),
				"HM-TPE"[exp - 1])
	}
	
	
}

