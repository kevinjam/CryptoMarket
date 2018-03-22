package com.thinkdevs.cryptomarket.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.FullListAdapter
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_full_list.*
import kotlinx.android.synthetic.main.toolbar.view.*

class FullListActivity : AppCompatActivity() {
	var composite:CompositeDisposable?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_full_list)
		composite = CompositeDisposable()
		
		full_list_toolbar.coinImage.visibility = View.GONE
		full_list_toolbar.nav_nack.setOnClickListener {
			onBackPressed()
		}
		full_list_toolbar.coinName.text =getString(R.string.market_capitalisation)
		full_list_toolbar.nav_share.visibility = View.GONE
		recyclerView3.setHasFixedSize(true)
		val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@FullListActivity)
		recyclerView3.layoutManager = layoutManager
		
		getfulllist()
	}
	
	private fun getfulllist() {
		progress.visibility = View.VISIBLE
		val service = Helper.mainUrl()
		composite!!.add(service.get_full_list()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe({
					success->
					progress.visibility = View.GONE
					getresponse(success)
					
				},{
					error->
				}))
		
	}
	
	private fun getresponse(list: ArrayList<CryptoModel>?) {
		
		val adapter = FullListAdapter(this@FullListActivity, list!!)
		recyclerView3.adapter = adapter
		
		
		val madapter: ArrayAdapter<CryptoModel> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, list)
		madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spinnercoin.adapter = madapter
		
		spinnercoin.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				val item = parent!!.getItemAtPosition(position)
				log("Item is $item")
			}
			
			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
			
		}

	}
	
	private fun log(msg: String) {
		Log.e(javaClass.simpleName, msg)
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
	}

}
