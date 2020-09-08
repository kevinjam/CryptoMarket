package com.thinkdevs.cryptomarket.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.BitCoinAdapter
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.Helper
import com.thinkdevs.cryptomarket.utils.SimpleDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class BitCoin : androidx.fragment.app.Fragment() {
	lateinit var bitcoin_rec: androidx.recyclerview.widget.RecyclerView
	lateinit var progress:ProgressBar
	var composite: CompositeDisposable?=null
	lateinit var no_Internet :TextView
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater!!.inflate(R.layout.bit_coin, container, false)
		bitcoin_rec = view.findViewById(R.id.bitcoin_rec)
		progress = view.findViewById(R.id.progress)
		no_Internet = view.findViewById(R.id.internet_error)

		bitcoin_rec.setHasFixedSize(true)
		val layoutManager : androidx.recyclerview.widget.RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
		bitcoin_rec.layoutManager = layoutManager
		bitcoin_rec.addItemDecoration(SimpleDividerItemDecoration(activity!!))
		
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
		val adapter = activity?.let { BitCoinAdapter(it, list) }
		bitcoin_rec.adapter = adapter
	
	}
	
	private fun handleError(error: Throwable) {
		progress.visibility = View.GONE
		bitcoin_rec.visibility=View.GONE
		no_Internet.visibility = View.VISIBLE
		
		no_Internet.text = getString(R.string.no_connection)
	}
	
	override fun onDestroy() {
		super.onDestroy()
		composite!!.clear()
	}
	
	
}
