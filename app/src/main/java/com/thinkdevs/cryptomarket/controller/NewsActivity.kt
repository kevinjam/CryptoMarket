package com.thinkdevs.cryptomarket.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.toolbar.view.*

class NewsActivity : AppCompatActivity() {
	var composit: CompositeDisposable? = null
	lateinit var recyclerView: RecyclerView
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_news)
		
		news_toolbar.coinImage.visibility = View.GONE
		news_toolbar.nav_nack.setOnClickListener { onBackPressed() }
		
		recyclerView = findViewById(R.id.recyclerView)
		val layoutManager =LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		composit = CompositeDisposable()
		
		getNews()
	}
	
	fun getNews() {
		progressbar.visibility = View.VISIBLE
		val service = Helper.newsUrl()
		composit!!.add(service.getnews()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe({ response ->
					if (response.status == "ok") {
						genewsdetails(response.articles)
						progressbar.visibility = View.GONE
						
					} else {
					
					}
				}, { error ->
					log("Errror ${error.message}")
					log("Errror ${error.printStackTrace()}")
					
					log("Errror ${error.localizedMessage}")
					
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
