package com.thinkdevs.cryptomarket.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.Crypto
import com.thinkdevs.cryptomarket.model.Helper
import im.dacer.androidcharts.LineView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * A simple [Fragment] subclass.
 */
class EutheremFragment : Fragment() {
	var composite: CompositeDisposable? = null
	var randomint = 9
	
	
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		val view = inflater!!.inflate(R.layout.fragment_news, container, false)
		composite = CompositeDisposable()
		getBitcoins()
		
		val lineView = view.findViewById(R.id.line_view_float) as LineView
		initLineView(lineView)
		randomSet(lineView)
		
		
		return view
	}
	
	fun getBitcoins() {
		val apiservice = Helper.mainUrl()
		composite!!.add(apiservice.get_ethereum()
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
	
	private fun handleError(error: Throwable?) {
		println("error $error")
		
	}
	
	private fun handlResponse(result: ArrayList<Crypto>) {
		println("Results goes here $result")
		
		result.forEach { model ->
			coin_name.text = model.name
			coin_prince.text = "$ ${model.price_usd}"
			symbol.text = model.symbol
			market_cap.text = "$ ${model.market_cap_usd}"
			volume.text = "$ ${model.max_supply}"
			available_supply.text = model.available_supply
			update.text = "Last Updated :${model.last_updated}"
			when {
				model.symbol == "LTC" -> {
					val resouldId = context.resources.getIdentifier("ic_ethereum", "drawable", activity.packageName)
					coinImage.setImageResource(resouldId)
				}
			}
			
			
		}
	}
	
	
	private fun initLineView(lineView: LineView) {
		val test = java.util.ArrayList<String>()
		for (i in 0 until randomint) {
			test.add((i + 1).toString())
		}
		lineView.setBottomTextList(test)
		lineView.setColorArray(intArrayOf(Color.parseColor("#F44336"),
				Color.parseColor("#9C27B0"), Color.parseColor("#2196F3"), Color.parseColor("#009688")))
		lineView.setDrawDotLine(true)
		lineView.setShowPopup(LineView.SHOW_POPUPS_NONE)
	}
	
	private fun randomSet(lineView: LineView) {
		val dataList = java.util.ArrayList<Int>()
		var random = (Math.random() * 9 + 1).toFloat()
		for (i in 0 until randomint) {
			dataList.add((Math.random() * random).toInt())
		}
		
		val dataList2 = java.util.ArrayList<Int>()
		random = (Math.random() * 9 + 1).toInt().toFloat()
		for (i in 0 until randomint) {
			dataList2.add((Math.random() * random).toInt())
		}
		
		val dataList3 = java.util.ArrayList<Int>()
		random = (Math.random() * 9 + 1).toInt().toFloat()
		for (i in 0 until randomint) {
			dataList3.add((Math.random() * random).toInt())
		}
		
		val dataLists = java.util.ArrayList<java.util.ArrayList<Int>>()
		dataLists.add(dataList)
		dataLists.add(dataList2)
		dataLists.add(dataList3)
		
		lineView.setDataList(dataLists)
		
		val dataListF = java.util.ArrayList<Float>()
		var randomF = (Math.random() * 9 + 1).toFloat()
		for (i in 0 until randomint) {
			dataListF.add((Math.random() * randomF).toFloat())
		}
		
		val dataListF2 = java.util.ArrayList<Float>()
		randomF = (Math.random() * 9 + 1).toInt().toFloat()
		for (i in 0 until randomint) {
			dataListF2.add((Math.random() * randomF).toFloat())
		}
		
		val dataListF3 = java.util.ArrayList<Float>()
		randomF = (Math.random() * 9 + 1).toInt().toFloat()
		for (i in 0 until randomint) {
			dataListF3.add((Math.random() * randomF).toFloat())
		}
		
		val dataListFs = java.util.ArrayList<java.util.ArrayList<Float>>()
		dataListFs.add(dataListF)
		dataListFs.add(dataListF2)
		dataListFs.add(dataListF3)
		
		lineView.setFloatDataList(dataListFs)
	}
	
}
