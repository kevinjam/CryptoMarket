package com.thinkdevs.cryptomarket.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_converter.*

class ConverterActivity : AppCompatActivity() {
	 var composite :CompositeDisposable? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_converter)
		
		composite = CompositeDisposable()
		
		getCrypto()
	}
	
	/**
	 * Get Crypto Details
	 */
	
	fun getCrypto(){
		val service =Helper.mainUrl()
		composite!!.add(service.getbitcoin()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{
							result->
							allDetails(result)
							
						},{
					error->
				}
				))
	}
	private fun allDetails(result: ArrayList<CryptoModel>?) {
		for(i in 0..0){
			result!!.add(CryptoModel("$i", ""))
		}
		
		
		val adapter:ArrayAdapter<CryptoModel> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, result)
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spinnerCoinName.adapter = adapter
		coinCal.adapter = adapter
		
		spinnerCoinName.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onNothingSelected(parent: AdapterView<*>?) {
			
			}
			
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				val currency:String = spinnerCoinName.getItemAtPosition(position).toString()
				log("Item Selected $currency")
				getCurrency(currency)
			}
			
		}
		
		
		
		
	}
	
	private fun getCurrency(currency: String) {
		
		
		val service = Helper.mainUrl()
		composite!!.add(service.get_ticket()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe({
					result ->
				},{
					error ->
				}))
		
	
	}
	
	private fun log(msg: String) {
		Log.e(javaClass.simpleName, msg)
	}
}
