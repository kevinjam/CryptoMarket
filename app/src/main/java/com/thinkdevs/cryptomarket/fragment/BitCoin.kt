package com.thinkdevs.cryptomarket.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.BitCoinAdapter
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class BitCoin : Fragment() {
	lateinit var bitcoin_rec: RecyclerView
	lateinit var progress:ProgressBar
	var composite: CompositeDisposable?=null
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater!!.inflate(R.layout.bit_coin, container, false)
		bitcoin_rec = view.findViewById(R.id.bitcoin_rec)
		progress = view.findViewById(R.id.progress)

		bitcoin_rec.setHasFixedSize(true)
		val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
		bitcoin_rec.layoutManager = layoutManager
		
		composite = CompositeDisposable()
		getBitcoins()
		return view
	}
	
	fun getBitcoins() {
		progress.visibility = View.VISIBLE
		bitcoin_rec.visibility=View.GONE
		val apiservice = Helper.mainUrl()
		composite!!.add(apiservice.getbitcoin()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{ result ->
							handlResponse(result)
							progress.visibility = View.GONE
							bitcoin_rec.visibility=View.VISIBLE
							
							
						}, { error ->
					handleError(error)
				}
				))
	}
	
	private fun handlResponse(list:ArrayList<CryptoModel>) {
		Log.e("","Gandler Response $list")
		val adapter = BitCoinAdapter(activity.applicationContext, list)
		bitcoin_rec.adapter = adapter
	
	}
	
	private fun handleError(error: Throwable) {
		Log.e("", "EEEEE")
		
	
	}
	
	override fun onDestroy() {
		super.onDestroy()
		composite!!.clear()
	}
	
	
}
