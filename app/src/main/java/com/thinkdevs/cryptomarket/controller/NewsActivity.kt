package com.thinkdevs.cryptomarket.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.ArticleAdapter
import com.thinkdevs.cryptomarket.model.Articles
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news_details.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class NewsActivity : AppCompatActivity() {
	var composit: CompositeDisposable? = null
	lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_news)
		
		news_toolbar.coinImage.visibility = View.GONE
		news_toolbar.nav_nack.setOnClickListener { onBackPressed() }
		news_toolbar.coinName.text = getString(R.string.latest_news)
		
		recyclerView = findViewById(R.id.recyclerView)
		val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		composit = CompositeDisposable()
		
		getNews()
	}
	
	fun getNews() {
		log("START GETTTING THE NEWS")
		progressbar.visibility = View.VISIBLE
		val service = Helper.newsUrl(this)
		composit!!.add(service.getnews()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe({ response ->
					log("I have the news now $response")
					if (response.status == "ok") {
						genewsdetails(response.articles)
						progressbar.visibility = View.GONE
						
					} else {
						log("No news boy")
					
					}
				}, { error ->
					no_internet.visibility = View.VISIBLE
					recyclerView.visibility = View.GONE
					progressbar.visibility = View.GONE
					
					no_internet.text =  getString(R.string.no_connection)
					
					
				})
		)
		
	}
	
	private fun genewsdetails(list: ArrayList<Articles>) {
		log("Success  $list")
		val adapter = ArticleAdapter(this, list)
		recyclerView.adapter = adapter
	}
	
	
	private fun log(msg: String) {
		Log.e(javaClass.simpleName, msg)
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
		
	}
	
	//Check this news : newsTitle
	
}
