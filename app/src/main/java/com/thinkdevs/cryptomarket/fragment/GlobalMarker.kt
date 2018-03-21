package com.thinkdevs.cryptomarket.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class GlobalMarker : Fragment() {
	var composite : CompositeDisposable?=null


	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		val view = inflater!!.inflate(R.layout.global_marker, container, false)
		composite = CompositeDisposable()
		
		globalMarket()
		return view
	}
	
	private fun globalMarket() {
		val apiService = Helper.mainUrl()
		composite!!.add(apiService.getbitcoin()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{
							result->
							getResult(result)
						},
						{
							error->
						}
				))
	
	}
	
	private fun getResult(result: ArrayList<CryptoModel>?) {
		log("result $result")
		var totalMarket:String = ""
		result!!.forEachIndexed { index, cryptoModel ->
			log("Total Market ${cryptoModel.max_supply}")
			totalMarket += cryptoModel.market_cap_usd
		}
		log("Total $totalMarket")
	}
	
	private fun log(msg:String){
		Log.e(javaClass.simpleName, msg)
	}
}