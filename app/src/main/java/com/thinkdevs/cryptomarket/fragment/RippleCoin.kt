package com.thinkdevs.cryptomarket.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.Crypto
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * A simple [Fragment] subclass.
 */
class RippleCoin : androidx.fragment.app.Fragment() {
	
	var composite: CompositeDisposable? = null
	
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		val view = inflater!!.inflate(R.layout.fragment_news, container, false)
		composite = CompositeDisposable()
		getBitcoins()
		return view
	}
	
	fun getBitcoins() {
		val apiservice = Helper.mainUrl()
		composite!!.add(apiservice.get_ripple()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{ result ->
							handlResponse(result)
							
							
						}, { error ->
					handleError(error)
				}
				))
	}
	
	
	
	private fun handlResponse(result: ArrayList<Crypto>) {
		println("Ripple ----Results goes here $result")
		
		result.forEach { model ->
			coin_name.text = model.name
			coin_prince.text = "$ ${model.price_usd}"
			symbol.text = model.symbol
			market_cap.text = model.market_cap_usd
			volume.text = model.percent_change_24h
			available_supply.text = model.available_supply
			update.text = "Last Updated :${model.last_updated}"
			
			when {
				model.symbol == "XRP" -> {
					val resouldId = context!!.resources.getIdentifier("ic_xrp", "drawable", activity!!.packageName)
					coinImage.setImageResource(resouldId)
				}
			}
		}
	}
	
	private fun handleError(error: Throwable?) {
		no_internet.visibility = View.VISIBLE
		no_internet.text = getString(R.string.no_connection)
		
	}
	
	
}// Required empty public constructor
