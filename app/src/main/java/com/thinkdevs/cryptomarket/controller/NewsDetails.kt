package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.NEWS_DETAILS
import com.thinkdevs.cryptomarket.model.Articles
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.net.Uri


class NewsDetails : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_news_details)
		
		news_toolbar.coinImage.visibility = View.GONE
		news_toolbar.nav_nack.setOnClickListener {
			onBackPressed()
		}
		
		val model = intent.getSerializableExtra(NEWS_DETAILS) as Articles
//
		news_title.text = model.source.name
		news_date.text = model.publishedAt
		news_title.text = model.title
		news_description.text = model.description
		
		news_toolbar.coinName.text =model.source.name
		
		
		Glide.with(this@NewsDetails).load(model.urlToImage).into(news_image)
		
		openweb.setOnClickListener {
			openWebPage(model.url)
		}
		

	}
	
	fun openWebPage(url: String) {
		try {
			val webpage = Uri.parse(url)
			val myIntent = Intent(Intent.ACTION_VIEW, webpage)
			startActivity(myIntent)
		} catch (e: ActivityNotFoundException) {
			Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show()
			e.printStackTrace()
		}
		
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
	}
}


